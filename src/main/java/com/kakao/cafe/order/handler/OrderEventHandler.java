package com.kakao.cafe.order.handler;

import com.kakao.cafe.global.service.MessageService;
import com.kakao.cafe.order.domain.Order;
import com.kakao.cafe.order.dto.OrderHistoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventHandler {

    private final MessageService messageService;

    /**
     * 집계서버 전송을 위한 비동기 Event Listener
     * @param history
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderHistoryDto.class)
    public void orderCompleteEventListener(OrderHistoryDto history) {
        try {
            log.debug("SEND Collection Server Event Listen : {}", history);
            messageService.sendCollectionServer(history);
        } catch (Exception e) { // 전송 실패시, Deferred File 처리. (실패건 후행 처리 목적)
            log.error("Collection Server Send Fail!, Write Deferred File ");
            e.printStackTrace();
            messageService.writeDeferredFile(history);
        }
    }
}

package com.kakao.cafe.global.service;

import com.kakao.cafe.order.dto.OrderHistoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final String DEFERRED_FILE_PATH = "./out/DEFERRED_MESSAGE.";

    /**
     * 집계서버 전송 API
     * @param orderHistory
     */
    public void sendCollectionServer(OrderHistoryDto orderHistory) {
//        try { Thread.sleep(5000); } catch (Exception ignore) { }
        log.debug("SEND COMPLETE COLLECTION SERVER : {} ", orderHistory);
//        throw new CustomException(ErrorCode.SERVER_ERROR); // 후행 처리 Error Exception 테스트
    }

    /**
     * 집계 서버 전송 실패시 후행 파일에 Write 한다.
     * @param orderHistory
     */
    public void writeDeferredFile(OrderHistoryDto orderHistory) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String fileName = DEFERRED_FILE_PATH + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String history = objectMapper.writeValueAsString(orderHistory) + "\n";
            log.debug("Write Deferred File : {} , JSON : {}", fileName, history);

            FileOutputStream fos = new FileOutputStream(fileName, true);
            fos.write(history.getBytes());
            fos.close();
        } catch (Exception e) {
            log.error("후행 파일 작성 실패 Exception : {}",e.getMessage());
            e.printStackTrace();
        }

    }


}

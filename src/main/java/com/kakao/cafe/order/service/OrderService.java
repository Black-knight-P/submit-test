package com.kakao.cafe.order.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.service.CustomerService;
import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.service.MenuService;
import com.kakao.cafe.order.domain.Order;
import com.kakao.cafe.order.dto.OrderHistoryDto;
import com.kakao.cafe.order.repository.OrderRepository;
import com.kakao.cafe.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final CustomerService customerService;
    private final PointService pointService;
    private final MenuService menuService;

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Order makeOrderProcess(String userId, Long menuId) throws Exception {
        Customer customer = customerService.findByUserId(userId);
        MenuItem orderedMenu = menuService.findByMenuId(menuId);
        Order order = Order.builder()
                .customer(customer)
                .menuItem(orderedMenu)
                .build();

        // 포인트 차감 처리
        pointService.deduct(userId, order.cost());
        Order completedOrder = orderRepository.save(order);
        log.debug("Make Order Process COMPLETE : {}", completedOrder);

        // 수집 플랫폼 서버 전송 비동기 이벤트 처리
        applicationEventPublisher.publishEvent(OrderHistoryDto.builder()
                .userId(userId)
                .price(order.cost())
                .menuName(orderedMenu.getName())
                .build());

        return completedOrder;
    }
}

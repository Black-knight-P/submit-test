package com.kakao.cafe.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.service.CustomerService;
import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.service.MenuService;
import com.kakao.cafe.order.domain.Order;
import com.kakao.cafe.order.repository.OrderRepository;
import com.kakao.cafe.order.service.OrderService;
import com.kakao.cafe.point.domain.Point;
import com.kakao.cafe.point.domain.PointWallet;
import com.kakao.cafe.point.service.PointService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import static java.util.Optional.ofNullable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @Mock
    private PointService pointService;

    @Mock
    private MenuService menuService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;


    @Test
    @DisplayName("주문을 생성한다")
    public void 주문을_생성한다() throws Exception {

        //given
        MenuItem menuItem = MenuItem.builder()
                .id(1L)
                .name("아메리카노")
                .price(3800)
                .build();

        Point point = new Point();
        point.charge(10000);
        PointWallet pointWallet = new PointWallet();
        ReflectionTestUtils.setField(pointWallet, "point", point);

        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("박지호")
                .encryptPassword("1234")
                .pointWallet(pointWallet)
                .build();

        Order order = Order.builder()
                .customer(customer)
                .menuItem(menuItem)
                .build();

        // mocking
        when(menuService.findByMenuId(any())).thenReturn(menuItem);
        when(customerService.findByUserId(any())).thenReturn(customer);
        when(orderRepository.findById(any())).thenReturn(ofNullable(order));
        given(orderRepository.save(any())).willReturn(order);

        // when
        Order makeOrder = orderService.makeOrderProcess("jiho", 1L);

        // then
        Assert.assertEquals(makeOrder.cost(), menuService.findByMenuId(1L).getPrice());
    }

}

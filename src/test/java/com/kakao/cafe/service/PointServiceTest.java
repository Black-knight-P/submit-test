package com.kakao.cafe.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.service.CustomerService;
import com.kakao.cafe.point.domain.Point;
import com.kakao.cafe.point.domain.PointWallet;
import com.kakao.cafe.point.service.PointService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private CustomerService customerService;

    @Test
    public void 포인트를_조회한다() throws Exception {
        //given
        Integer amount = 10000;
        Point point = new Point();
        point.charge(amount);
        PointWallet pointWallet = new PointWallet();
        ReflectionTestUtils.setField(pointWallet, "point", point);

        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("박지호")
                .encryptPassword("1234")
                .pointWallet(pointWallet)
                .build();

        //mocking
        when(customerService.findByUserId(any())).thenReturn(customer);

        //when
        Integer balance = pointService.getBalance("jiho");

        //then
        assertEquals(balance, amount);
    }

    @Test
    public void 포인트를_충전한다() throws Exception {

        //given
        Integer amount = 10000;
        Point point = new Point();
        point.charge(amount);
        PointWallet pointWallet = new PointWallet();
        ReflectionTestUtils.setField(pointWallet, "point", point);

        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("박지호")
                .encryptPassword("1234")
                .pointWallet(pointWallet)
                .build();

        //mocking
        when(customerService.findByUserId(any())).thenReturn(customer);

        //when
        pointService.charge("jiho", amount);

        //then
        verify(customerService, atLeastOnce()).findByUserId("jiho");
    }

    @Test
    public void 포인트를_차감한다() throws Exception {

        //given
        Integer amount = 10000;
        Point point = new Point();
        point.charge(amount);
        PointWallet pointWallet = new PointWallet();
        ReflectionTestUtils.setField(pointWallet, "point", point);

        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("박지호")
                .encryptPassword("1234")
                .pointWallet(pointWallet)
                .build();

        //mocking
        when(customerService.findByUserId(any())).thenReturn(customer);

        //when
        pointService.deduct("jiho", amount);

        //then
        verify(customerService, atLeastOnce()).findByUserId("jiho");
    }
}

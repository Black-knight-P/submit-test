package com.kakao.cafe.domain;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.global.util.SecureUtils;
import com.kakao.cafe.point.domain.PointWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    @DisplayName("정상적으로 고객 객체를 생성한다.")
    public void 고객_객체_생성() {
        assertDoesNotThrow(() -> Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build());
    }

    @Test
    @DisplayName("고객의 포인트 잔액을 확인한다.")
    public void 고객_포인트_잔액_조회() throws Exception {

        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        assertEquals(customer.getMyPointBalance(), 0);
    }


    @Test
    @DisplayName("고객의 포인트 잔액을 충전한다.")
    public void 고객_포인트_잔액_충전() throws Exception {

        //given
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        int amount = 10000;
        //when
        customer.chargePoints(amount);

        //then
        assertEquals(customer.getMyPointBalance(), amount);
    }

    @Test
    @DisplayName("고객의 포인트 잔액을 차감한다.")
    public void 고객_포인트_잔액_차감() throws Exception {

        //given
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        //when
        int amount = 10000;
        int minusAmount = 100;
        customer.chargePoints(amount);
        customer.deductPoints(minusAmount);

        //then
        assertEquals(customer.getMyPointBalance(), amount - minusAmount);
    }


}

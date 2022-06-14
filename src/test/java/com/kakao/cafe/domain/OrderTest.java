package com.kakao.cafe.domain;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.global.util.SecureUtils;
import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.order.domain.Order;
import com.kakao.cafe.point.domain.PointWallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void 주문_객체_생성() throws Exception {
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("아메리카노")
                .price(3800)
                .build();

        assertDoesNotThrow(() ->Order.builder()
                .customer(customer)
                .menuItem(menuItem)
                .build());

    }

    @Test
    public void 주문_금액_확인() throws Exception {
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("아메리카노")
                .price(3800)
                .build();

        Order order = Order.builder()
                .customer(customer)
                .menuItem(menuItem)
                .build();

        assertEquals(order.cost(), menuItem.getPrice());
    }


    @Test
    public void 주문_고객ID_확인() throws Exception {
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("아메리카노")
                .price(3800)
                .build();

        Order order = Order.builder()
                .customer(customer)
                .menuItem(menuItem)
                .build();

        assertEquals(order.customerUserId(), customer.getUserId());
    }

    @Test
    public void 주문_메뉴_이름_확인() throws Exception {
        Customer customer = Customer.builder()
                .userId("jiho")
                .userName("jiho")
                .encryptPassword(SecureUtils.encrypt("1234"))
                .pointWallet(new PointWallet())
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("아메리카노")
                .price(3800)
                .build();

        Order order = Order.builder()
                .customer(customer)
                .menuItem(menuItem)
                .build();

        assertEquals(order.saleMenuName(), menuItem.getName());
    }
}

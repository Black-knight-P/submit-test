package com.kakao.cafe.domain;

import com.kakao.cafe.menu.domain.MenuItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MenuItemTest {

    @Test
    public void 주문_객체_생성() {

        assertDoesNotThrow(() -> MenuItem.builder()
                .name("아메리카노")
                .price(3800)
                .build());
    }
}

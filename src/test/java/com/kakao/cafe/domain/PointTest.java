package com.kakao.cafe.domain;

import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.point.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {

    @Test
    @DisplayName("정상적으로 Point 객체를 생성한다.")
    public void 포인트_객체_생성() {
        assertDoesNotThrow(() -> new Point());
    }

    @Test
    @DisplayName("현재 잔액보다 많은 금액을 차감 하려고 할때 Exception 발생")
    public void 포인트_잔액초과_테스트() {

        Point point = new Point();
        point.charge(1000);
        assertThrows(CustomException.class,() -> point.deduct(2000));
    }

}

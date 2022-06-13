package com.kakao.cafe.point.domain;

import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Getter
@NoArgsConstructor
@Embeddable
public class Point {
    @Column(name = "balance")
    private int balance; // 잔액

    public void deduct(Integer points) throws Exception {
        this.balance -= points;
        if (this.balance < 0) throw new CustomException(ErrorCode.REJECT_POINT_PAYMENT);
    }

    public void charge(Integer points) {
        this.balance += points;
    }
}

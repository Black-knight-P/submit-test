package com.kakao.cafe.point.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@ToString
@Slf4j
@Entity
@Getter
@Table(name = "point_wallet")
public class PointWallet {

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Point point;

    public PointWallet() {
        this.point = new Point();
    }

    public Integer getPoints() {
        return point.getBalance();
    }

    public void deductPoints(Integer deductedPoints) throws Exception {
        point.deduct(deductedPoints);
    }

    public void chargePoints(Integer points) {
        point.charge(points);
    }

}

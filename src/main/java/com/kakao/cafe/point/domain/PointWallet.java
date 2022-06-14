package com.kakao.cafe.point.domain;

import lombok.Getter;
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

    @Version
    @Column(name = "version", nullable = false)
    private long version = 0L;

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

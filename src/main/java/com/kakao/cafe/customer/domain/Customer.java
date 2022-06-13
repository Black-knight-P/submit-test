package com.kakao.cafe.customer.domain;

import com.kakao.cafe.point.domain.PointWallet;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;


@ToString(exclude = {"id","password"})
@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "customer_id")
    private long id;

    @Getter
    @Column(name = "user_id", length = 100, unique = true)
    private String userId;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Getter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private PointWallet pointWallet;

    @Builder
    public Customer (String userId, String userName, PointWallet pointWallet, String encryptPassword) {
        this.userId = userId;
        this.userName = userName;
        this.pointWallet = pointWallet;
        this.password = encryptPassword;
    }

    public void deductPoints(Integer deductedPoint) throws Exception {
        this.pointWallet.deductPoints(deductedPoint);
    }

    public void chargePoints(Integer points) {
        this.pointWallet.chargePoints(points);
    }

    public Integer getMyPointBalance() {
        return this.pointWallet.getPoints();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

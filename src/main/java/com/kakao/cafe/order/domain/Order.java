package com.kakao.cafe.order.domain;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.menu.domain.MenuItem;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@ToString
@Entity
@NoArgsConstructor
@Table(name = "orders",
        indexes = {@Index(name = "ix_order_date_time", columnList = "order_date_time")})
public class Order {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Builder
    public Order(Customer customer, MenuItem menuItem) {
        this.menuItem = menuItem;
        this.customer = customer;
        this.orderDateTime = LocalDateTime.now();
    }

    public Integer cost() {
        return menuItem.getPrice();
    }
    public String saleMenuName() { return menuItem.getName(); }
    public String customerUserId() { return customer.getUserId(); }
}

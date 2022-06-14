package com.kakao.cafe.point.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PointService {

    private final CustomerService customerService;

    @Transactional
    public void deduct(String userId, Integer points) throws Exception {
        Customer customer = findCustomerByUserId(userId);
        customer.deductPoints(points);
    }

    @Transactional
    public Integer getBalance(String userId) throws Exception {
        Customer customer = findCustomerByUserId(userId);
        return customer.getMyPointBalance();
    }

    @Transactional
    public void charge(String userId, Integer points) throws Exception {
        Customer customer = findCustomerByUserId(userId);
        customer.chargePoints(points);
    }

    @Transactional
    public Customer findCustomerByUserId(String userId) throws Exception {
        return customerService.findByUserId(userId);
    }

}

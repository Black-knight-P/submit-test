package com.kakao.cafe.customer.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.repository.CustomerRepository;
import com.kakao.cafe.global.util.SecureUtils;
import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import com.kakao.cafe.point.domain.PointWallet;
import com.kakao.cafe.point.repository.PointWalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PointWalletRepository pointWalletRepository;

    public Customer findByUserId(String userId) {
        return customerRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CUSTOMER));
    }

    @Transactional
    public Customer register(String userId, String userName, String password) throws Exception {

        customerRepository.findByUserId(userId)
                .ifPresent(customer -> { throw new CustomException(ErrorCode.ALREADY_REGISTERED_CUSTOMER); });

        PointWallet pointWallet = pointWalletRepository.save(new PointWallet());
        Customer customer = customerRepository.save(Customer.builder()
                .userId(userId)
                .userName(userName)
                .encryptPassword(SecureUtils.encrypt(password))
                .pointWallet(pointWallet)
                .build());
        log.debug("Created Customer Entity : {} ", customer);

        return customer;
    }

}

package com.kakao.cafe.service;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.repository.CustomerRepository;
import com.kakao.cafe.customer.service.CustomerService;
import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import com.kakao.cafe.point.domain.PointWallet;
import com.kakao.cafe.point.repository.PointWalletRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PointWalletRepository pointWalletRepository;

    @Test
    @DisplayName("회원등록_테스트")
    public void 회원등록_테스트() throws Exception {

        //given
        Customer customer = Customer.builder()
                .userId("jhpar8")
                .userName("박지호")
                .encryptPassword("1234")
                .pointWallet(new PointWallet())
                .build();

        ReflectionTestUtils.setField(customer, "id", 1L);

        //mocking
        given(customerRepository.save(any())).willReturn(customer);
        given(pointWalletRepository.save(any())).willReturn(new PointWallet());
        when(customerRepository.findById(any())).thenReturn(java.util.Optional.of(customer));

        //when
        Customer register = customerService.register("jhpar8", "박지호", "1234");
        Customer findCustomer = customerRepository.findById(register.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CUSTOMER));

        //then
        assertEquals(register.getUserId(), findCustomer.getUserId());
        assertEquals(register, findCustomer);
    }


}

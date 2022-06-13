package com.kakao.cafe.customer.controller;

import com.kakao.cafe.customer.domain.Customer;
import com.kakao.cafe.customer.dto.CustomerDto;
import com.kakao.cafe.customer.service.CustomerService;
import com.kakao.cafe.global.dto.CustomResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    @ApiOperation(value = "회원 가입 API", notes = "신규 회원 가입을 처리한다.")
    public CustomResponse register(@RequestBody @Valid CustomerDto.Register register) throws Exception {
        Customer newCustomer = customerService.register(register.getUserId(), register.getUserName(), register.getPassword());
        log.debug("Register Customer : {}",newCustomer);
        return CustomResponse.createNormalResponse();
    }

}

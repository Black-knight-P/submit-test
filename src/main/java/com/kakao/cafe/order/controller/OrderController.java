package com.kakao.cafe.order.controller;

import com.kakao.cafe.global.dto.CustomResponse;
import com.kakao.cafe.order.dto.OrderRequestDto;
import com.kakao.cafe.order.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make-payment")
    @ApiOperation(value = "커피 주문, 결제하기 API", notes = "주문 및 결제를 처리한다.")
    private CustomResponse makeOrderAndPayment(@Valid @RequestBody OrderRequestDto dto) throws Exception {
        orderService.makeOrderProcess(dto.getUserId(), dto.getMenuItemId());
        return CustomResponse.createNormalResponse();
    }

}

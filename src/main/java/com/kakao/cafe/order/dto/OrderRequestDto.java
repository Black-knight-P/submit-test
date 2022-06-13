package com.kakao.cafe.order.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 주문 생성을 요청 처리를 위한 DTO
 */
@Data
public class OrderRequestDto {

    @NotEmpty
    private String userId;
    @NotNull
    private Long menuItemId;

}

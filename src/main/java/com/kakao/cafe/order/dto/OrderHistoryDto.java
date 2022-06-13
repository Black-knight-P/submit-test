package com.kakao.cafe.order.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 주문 내역을 집계 서버로 전송을 위한 DTO
 */
@Builder
@Data
public class OrderHistoryDto {

    private String userId;
    private String menuName;
    private Integer price;

}

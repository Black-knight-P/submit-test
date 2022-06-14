package com.kakao.cafe.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointDto {
    @NotEmpty
    private String userId;     // 고객 식별 ID
    @NotNull
    private Integer points;    // 충전 금액
}

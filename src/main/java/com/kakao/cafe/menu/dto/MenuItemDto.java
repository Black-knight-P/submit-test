package com.kakao.cafe.menu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private Integer price;
}

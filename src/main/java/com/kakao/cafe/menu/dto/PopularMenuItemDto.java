package com.kakao.cafe.menu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PopularMenuItemDto {
    private Long menuItemId;
    private String menuName;
    private Long saleCount;
}

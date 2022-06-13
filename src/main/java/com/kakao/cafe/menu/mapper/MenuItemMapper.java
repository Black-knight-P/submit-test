package com.kakao.cafe.menu.mapper;

import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.dto.MenuItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    MenuItemDto menuItemToMenuItemDto(MenuItem menuItem);

}

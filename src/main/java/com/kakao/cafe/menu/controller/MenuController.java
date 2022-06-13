package com.kakao.cafe.menu.controller;

import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.dto.MenuItemDto;
import com.kakao.cafe.menu.dto.PopularMenuItemDto;
import com.kakao.cafe.menu.mapper.MenuItemMapper;
import com.kakao.cafe.menu.service.MenuService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    private final MenuItemMapper menuItemMapper;

    @GetMapping("/all")
    @ApiOperation(value = "커피 메뉴 목록 조회 API", notes = "등록된 전체 메뉴를 조회한다.")
    public List<MenuItemDto> getAllMenus() {
        List<MenuItem> allMenus = menuService.getAllMenus();
        return allMenus.stream()
                .map(menuItemMapper::menuItemToMenuItemDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/popular-coffee")
    @ApiOperation(value = "인기메뉴 목록 조회 API", notes = "7일간의 판매 내역 중 상위 3개의 상품을 리턴한다.")
    public List<PopularMenuItemDto> getPopularMenus() {
        List<PopularMenuItemDto> popularMenus = menuService.getPopularMenus();
        log.debug("getPopularMenus : {}", popularMenus);
        return popularMenus;
    }

}

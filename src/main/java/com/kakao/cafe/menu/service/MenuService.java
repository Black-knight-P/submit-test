package com.kakao.cafe.menu.service;

import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.dto.PopularMenuItemDto;
import com.kakao.cafe.menu.repository.MenuCustomQueryRepository;
import com.kakao.cafe.menu.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;
    private final MenuCustomQueryRepository menuCustomQueryRepository;

    /***
     * 판매중인 메뉴 전체를 조회한다.
     * @return List<MenuItem>
     */
    public List<MenuItem> getAllMenus() {
        return menuItemRepository.findAll();
    }

    public MenuItem findByMenuId(Long menuId) {
        return menuItemRepository.findById(menuId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MENU_ITEM));
    }

    /***
     * 인기 상품을 조회 한다.
     * @return
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<PopularMenuItemDto> getPopularMenus() {
        return menuCustomQueryRepository.getPopularMenus();
    }

}

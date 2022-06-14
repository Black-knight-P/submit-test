package com.kakao.cafe.service;

import com.kakao.cafe.menu.domain.MenuItem;
import com.kakao.cafe.menu.dto.PopularMenuItemDto;
import com.kakao.cafe.menu.repository.MenuCustomQueryRepository;
import com.kakao.cafe.menu.repository.MenuItemRepository;
import com.kakao.cafe.menu.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuCustomQueryRepository menuCustomQueryRepository;

    @Test
    public void 전체_메뉴_목록_조회_테스트() {

        //given
        MenuItem menuItem1 = MenuItem.builder().name("아메리카노").price(3800).build();
        MenuItem menuItem2 = MenuItem.builder().name("카페라떼").price(3800).build();
        MenuItem menuItem3 = MenuItem.builder().name("카페모카").price(3800).build();
        MenuItem menuItem4 = MenuItem.builder().name("카푸치노").price(3800).build();
        MenuItem menuItem5 = MenuItem.builder().name("바닐라라떼").price(3800).build();

        ReflectionTestUtils.setField(menuItem1, "id", 1L);
        ReflectionTestUtils.setField(menuItem2, "id", 2L);
        ReflectionTestUtils.setField(menuItem3, "id", 3L);
        ReflectionTestUtils.setField(menuItem4, "id", 4L);
        ReflectionTestUtils.setField(menuItem5, "id", 5L);


        //mocking
        when(menuItemRepository.findAll()).thenReturn(List.of(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5));

        //when
        List<MenuItem> allMenusByService = menuService.getAllMenus();
        List<MenuItem> allMenusByRepository = menuItemRepository.findAll();

        //then
        assertEquals(allMenusByService, allMenusByRepository);

    }

    @Test
    public void 인기_상품_조회_테스트() {

        //given
        PopularMenuItemDto temp1 = new PopularMenuItemDto();
        PopularMenuItemDto temp2 = new PopularMenuItemDto();
        PopularMenuItemDto temp3 = new PopularMenuItemDto();

        ReflectionTestUtils.setField(temp1, "menuItemId", 1L);
        ReflectionTestUtils.setField(temp1, "menuName", "아메리카노");
        ReflectionTestUtils.setField(temp1, "saleCount", 24L);

        ReflectionTestUtils.setField(temp2, "menuItemId", 1L);
        ReflectionTestUtils.setField(temp2, "menuName", "카페라떼");
        ReflectionTestUtils.setField(temp2, "saleCount", 20L);

        ReflectionTestUtils.setField(temp3, "menuItemId", 1L);
        ReflectionTestUtils.setField(temp3, "menuName", "카페모카");
        ReflectionTestUtils.setField(temp3, "saleCount", 18L);

        //mocking
        when(menuCustomQueryRepository.getPopularMenus()).thenReturn(List.of(temp1, temp2, temp3));

        //when
        List<PopularMenuItemDto> popularMenus = menuService.getPopularMenus();

        //then
        assertEquals(popularMenus.get(0).getMenuItemId(),temp1.getMenuItemId());
        assertEquals(popularMenus.get(0).getMenuName(),temp1.getMenuName());
        assertEquals(popularMenus.get(0).getSaleCount(),temp1.getSaleCount());
        assertEquals(popularMenus.get(1).getMenuItemId(),temp2.getMenuItemId());
        assertEquals(popularMenus.get(1).getMenuName(),temp2.getMenuName());
        assertEquals(popularMenus.get(1).getSaleCount(),temp2.getSaleCount());
        assertEquals(popularMenus.get(2).getMenuItemId(),temp3.getMenuItemId());
        assertEquals(popularMenus.get(2).getMenuName(),temp3.getMenuName());
        assertEquals(popularMenus.get(2).getSaleCount(),temp3.getSaleCount());
    }
}

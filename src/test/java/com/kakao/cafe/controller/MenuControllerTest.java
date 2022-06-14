package com.kakao.cafe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("메뉴 목록 전체 조회 API 테스트")
    public void 메뉴목록_전체_API_조회_테스트() throws Exception {

        mvc.perform(
                get("/menu/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("아메리카노"))
                .andExpect(jsonPath("$.[0].price").value(3500))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].name").value("카페라떼"))
                .andExpect(jsonPath("$.[1].price").value(4000))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[2].name").value("카페모카"))
                .andExpect(jsonPath("$.[2].price").value(4500))
                .andExpect(jsonPath("$.[3].id").value(4))
                .andExpect(jsonPath("$.[3].name").value("카푸치노"))
                .andExpect(jsonPath("$.[3].price").value(4600))
                .andExpect(jsonPath("$.[4].id").value(5))
                .andExpect(jsonPath("$.[4].name").value("바닐라라떼"))
                .andExpect(jsonPath("$.[4].price").value(51000));
    }

    @Test
    @DisplayName("인기메뉴 목록 조회 API")
    public void 최근_7일간_인기상품_조회() throws Exception {

        mvc.perform(
                get("/menu/popular-coffee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].menuItemId").value(2))
                .andExpect(jsonPath("$.[0].menuName").value("카페라떼"))
                .andExpect(jsonPath("$.[0].saleCount").value(19))
                .andExpect(jsonPath("$.[1].menuItemId").value(3))
                .andExpect(jsonPath("$.[1].menuName").value("카페모카"))
                .andExpect(jsonPath("$.[1].saleCount").value(18))
                .andExpect(jsonPath("$.[2].menuItemId").value(1))
                .andExpect(jsonPath("$.[2].menuName").value("아메리카노"))
                .andExpect(jsonPath("$.[2].saleCount").value(10));

    }


}

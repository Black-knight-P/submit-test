package com.kakao.cafe.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class AggregationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
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

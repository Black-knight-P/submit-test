package com.kakao.cafe.controller;

import com.kakao.cafe.global.exception.ErrorCode;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("주문 API 테스트 정상")
    public void 주문_API_테스트_정상() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("menuItemId", "1");

        mvc.perform(
                post("/order/make")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("COMPLETE"))
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    @DisplayName("주문 API 테스트 실패 포인트 잔액 부족")
    public void 주문_API_테스트_실패_포인트_잔액부족() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("menuItemId", "5");

        mvc.perform(
                post("/order/make")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value(ErrorCode.REJECT_POINT_PAYMENT.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.REJECT_POINT_PAYMENT.getCode()))
                .andExpect(jsonPath("$.status").value(ErrorCode.REJECT_POINT_PAYMENT.getStatus()));
    }

    @Test
    @DisplayName("주문 API 테스트 오류 회원정보 없음")
    public void 주문_API_테스트_오류_회원정보_에러() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho222");
        jsonObject.put("menuItemId", "1");

        mvc.perform(
                post("/order/make")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value(ErrorCode.NOT_FOUND_CUSTOMER.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.NOT_FOUND_CUSTOMER.getCode()))
                .andExpect(jsonPath("$.status").value(ErrorCode.NOT_FOUND_CUSTOMER.getStatus()));
    }

    @Test
    @DisplayName("주문 API 테스트 검증")
    public void 주문_API_테스트_필드_껌증() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "");
        jsonObject.put("menuItemId", 1);

        mvc.perform(
                post("/order/make")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userId").value("must not be empty"));
    }

}

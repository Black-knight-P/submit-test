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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("회원가입 API 테스트 정상")
    public void 회원가입_API_테스트_정상() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "solha");
        jsonObject.put("userName", "박솔하");
        jsonObject.put("password", "1234");

        mvc.perform(
                post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("COMPLETE"))
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    @DisplayName("회원가입 API 테스트 오류")
    public void 회원가입_API_테스트_기가입자() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("userName", "박지호");
        jsonObject.put("password", "1234");

        mvc.perform(
                post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value(ErrorCode.ALREADY_REGISTERED_CUSTOMER.getMessage()))
                .andExpect(jsonPath("$.status").value(ErrorCode.ALREADY_REGISTERED_CUSTOMER.getStatus()))
                .andExpect(jsonPath("$.code").value(ErrorCode.ALREADY_REGISTERED_CUSTOMER.getCode()));
    }

    @Test
    @DisplayName("회원가입 API 테스트 검증")
    public void 회원가입_API_테스트_필드_검증() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "");
        jsonObject.put("userName", "박지호");
        jsonObject.put("password", "1234");

        mvc.perform(
                post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userId").value("must not be empty"));
    }

    @Test
    @DisplayName("회원가입 API 테스트 검증")
    public void 회원가입_API_테스트_필드_검증_2() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("userName", "");
        jsonObject.put("password", "1234");

        mvc.perform(
                post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userName").value("must not be empty"));
    }
}

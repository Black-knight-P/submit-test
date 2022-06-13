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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class PointControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("회원포인트충전 API 테스트 정상")
    public void 회원포인트충전_API_테스트_정상() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("points", "10000");

        mvc.perform(
                put("/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("COMPLETE"))
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    @DisplayName("회원포인트조회 API 테스트 정상")
    public void 회원포인트조회_API_테스트_정상() throws Exception {

        mvc.perform(
                get("/point/my")
                        .param("userId", "jiho"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("jiho"))
                .andExpect(jsonPath("$.points").value(5000));
    }

    @Test
    @DisplayName("회원포인트 충전 후 조회 API 테스트 정상")
    public void 회원포인트_충전_후_조회_API_테스트_정상() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho");
        jsonObject.put("points", "10000");

        mvc.perform(
                put("/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("COMPLETE"))
                .andExpect(jsonPath("$.status").value(200));

        mvc.perform(
                get("/point/my")
                        .param("userId", "jiho"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("jiho"))
                .andExpect(jsonPath("$.points").value(15000));
    }


    @Test
    @DisplayName("회원포인트조회API 테스트 오류")
    public void 회원포인트조회_API_테스트_미존재아이디() throws Exception {
        mvc.perform(
                get("/point/my")
                        .param("userId", "jiho22"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value(ErrorCode.NOT_FOUND_CUSTOMER.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.NOT_FOUND_CUSTOMER.getCode()))
                .andExpect(jsonPath("$.status").value(ErrorCode.NOT_FOUND_CUSTOMER.getStatus()));
    }

    @Test
    @DisplayName("회원포인트충전 API 테스트 오류")
    public void 회원포인트충전_API_테스트_미존재아이디() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "jiho22");
        jsonObject.put("points", "10000");

        mvc.perform(
                put("/point/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value(ErrorCode.NOT_FOUND_CUSTOMER.getMessage()))
                .andExpect(jsonPath("$.code").value(ErrorCode.NOT_FOUND_CUSTOMER.getCode()))
                .andExpect(jsonPath("$.status").value(ErrorCode.NOT_FOUND_CUSTOMER.getStatus()));
    }


}

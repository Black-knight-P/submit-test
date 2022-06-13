package com.kakao.cafe.global.dto;

import com.kakao.cafe.global.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomResponse {
    private String message; // 예외 메시지 저장
    private String code;    // Error Code
    private int status;     // HTTP Status Code

    @Builder
    private CustomResponse(String message, String code, int status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public static CustomResponse createNormalResponse() {
        return CustomResponse.builder()
                .message("COMPLETE")
                .status(HttpStatus.OK.value())
                .build();
    }

    public static CustomResponse createNormalResponse(String message) {
        return CustomResponse.builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .build();
    }

    public static CustomResponse createErrorResponse(CustomException e) {
        return CustomResponse.builder()
                .message(e.getErrorCode().getMessage())
                .code(e.getErrorCode().getCode())
                .status(e.getErrorCode().getStatus())
                .build();
    }

}
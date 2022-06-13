package com.kakao.cafe.customer.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class CustomerDto {

    @Getter
    public static class Register {
        @NotEmpty
        private String userId;
        @NotEmpty
        private String userName;
        @NotEmpty
        private String password;
    }

}

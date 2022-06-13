package com.kakao.cafe.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 회원 관련 Error
    NOT_FOUND_CUSTOMER(404, "E0001", "해당 회원이 존재하지 않습니다."),
    ALREADY_REGISTERED_CUSTOMER(400, "E0002", "기 가입된 아이디 정보가 있습니다."),

    // 포인트 처리 관련 Error
    REJECT_POINT_PAYMENT(403, "E1001","포인트 잔액부족으로 결제에 실패했습니다."),
    NOT_FOUND_POINT_WALLET(404, "E1002","포인트 지갑이 존재하지 않습니다."),

    // 메뉴 관련 Error
    NOT_FOUND_MENU_ITEM(404, "E2001", "해당 메뉴는 존재하지 않습니다."),

    // 주문 관련 Error
    NOT_FOUND_ORDER(404, "E3001", "해당 주문은 존재하지 않습니다."),

    // 비정상 Runtime Exception
    SERVER_ERROR(500, "E9999", "처리중 오류가 발생하였습니다.");

    private final int status;
    private final String code;
    private final String message;

}

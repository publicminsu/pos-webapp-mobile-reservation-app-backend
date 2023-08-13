package com.hknusc.web.util.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String) {
    MENU_NOT_SAVED(HttpStatus.BAD_REQUEST, "메뉴가 저장되지 못했습니다."),
    NOT_PHOTO_REQUEST(HttpStatus.BAD_REQUEST, "이미지 형식이 아닙니다."),
    BAD_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 인증입니다."),
    RESERVATION_WRONG_CODE(HttpStatus.BAD_REQUEST, "잘못된 예약 상태입니다."),
    TABLE_NOT_EMPTY(HttpStatus.BAD_REQUEST, "이미 사용 중인 테이블입니다."),
    PASSWORD_NOT_SAME(HttpStatus.BAD_REQUEST, "입력하신 비밀번호가 동일하지 않습니다."),

    USER_EDIT_FAIL(HttpStatus.BAD_REQUEST, "회원 정보 수정에 실패했습니다."),
    RESERVATION_APPROVE_FAIL(HttpStatus.BAD_REQUEST, "예약 처리에 실패했습니다."),

    SIGNUP_FAIL(HttpStatus.UNAUTHORIZED, "회원가입에 실패했습니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증입니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "인증되지 않았습니다."),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 인증입니다."),

    STORE_NOT_OPEN(HttpStatus.UNAUTHORIZED, "개점하지 않았습니다."),

    FORBIDDEN_TOKEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "상점을 찾을 수 없습니다."),
    TABLE_NOT_FOUND(HttpStatus.NOT_FOUND, "테이블을 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문 내역을 찾을 수 없습니다."),
    ORDER_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "상세 주문 내역을 찾을 수 없습니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약을 찾을 수 없습니다."),

    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이메일이 중복되었습니다."),
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "닉네임이 중복되었습니다."),
    PHONE_NUMBER_DUPLICATE(HttpStatus.CONFLICT, "전화번호가 중복되었습니다."),

    FILE_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "파일의 크기가 너무 큽니다.")
}
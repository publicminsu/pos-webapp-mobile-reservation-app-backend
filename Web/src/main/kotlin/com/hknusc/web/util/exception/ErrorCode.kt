package com.hknusc.web.util.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MENU_NOT_SAVED(HttpStatus.BAD_REQUEST, "메뉴가 저장되지 못했습니다."),
    NOT_PHOTO_REQUEST(HttpStatus.BAD_REQUEST, "이미지 형식이 아닙니다."),
    BAD_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 인증입니다."),

    USER_EDIT_FAIL(HttpStatus.BAD_REQUEST,"회원 정보 수정이 안 됐습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "상점을 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."),

    SIGNUP_FAIL(HttpStatus.UNAUTHORIZED, "회원가입에 실패했습니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증입니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "인증되지 않았습니다."),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 인증입니다."),

    STORE_NOT_OPEN(HttpStatus.UNAUTHORIZED, "개점하지 않았습니다."),

    FORBIDDEN_TOKEN(HttpStatus.FORBIDDEN,"권한이 없습니다."),

    FILE_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "파일의 크기가 너무 큽니다.")
}
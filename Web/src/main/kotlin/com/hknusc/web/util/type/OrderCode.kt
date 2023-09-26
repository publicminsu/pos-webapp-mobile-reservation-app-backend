package com.hknusc.web.util.type

enum class OrderCode(val label: String, val code: Short) {
    RESERVATION("예약 확정", 101),
    RESERVATION_DENY("예약 거부", 102),
    RESERVATION_WAIT("예약 승인 대기", 103),
    RESERVATION_CANCEL("예약 취소", 104),
    ORDER("주문", 201),
    SERVED("서빙 완료", 301),
    PAYMENT("결제", 401);

    companion object {
        fun find(code: Short) = OrderCode.values().find { it.code == code }
    }
}

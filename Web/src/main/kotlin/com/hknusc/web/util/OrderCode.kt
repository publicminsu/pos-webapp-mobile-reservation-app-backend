package com.hknusc.web.util

enum class OrderCode(val label: String) {
    RESERVATION("예약"),
    RESERVATION_DENY("예약 거부"),
    RESERVATION_WAIT("예약 승인 대기"),
    ORDER("주문"),
    SERVED("서빙 완료"),
    PAYMENT("결제")
}
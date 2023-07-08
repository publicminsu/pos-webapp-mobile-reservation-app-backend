package com.hknusc.web.dto

import java.sql.Timestamp

//reservationTime 있다면 예약 신청
//isReservation True 예약 신청 완료
//isReservation False 예약 대기
//reservationDenyDetail 예약 거부
//orderTime 있다면 주문
data class OrderDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var orderTime: Timestamp?,
    var paymentTime: Timestamp?,
    var reservationTime: Timestamp?,
    var isServed: Boolean = false,
    var isReservation: Boolean = false,
    var reservationDenyDetail: String?
)

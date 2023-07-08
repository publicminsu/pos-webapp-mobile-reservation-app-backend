package com.hknusc.web.dto

import java.sql.Timestamp

data class ReservationApplyDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var isServed: Boolean = false,
    var isReservation: Boolean = true,
)

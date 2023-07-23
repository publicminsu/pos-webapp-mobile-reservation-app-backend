package com.hknusc.web.dto.order

import java.sql.Timestamp

data class OrderSaveDTO(
    var accountId: Int,
    var tableId: Int,
    var orderTime: Timestamp?,
    var paymentTime: Timestamp?,
    var reservationTime: Timestamp?,
    var isServed: Boolean = false,
    var isReservation: Boolean = false,
    var reservationDenyDetail: String?
)

package com.hknusc.web.dto.order

import java.sql.Timestamp

data class OrderDBSaveDTO(
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
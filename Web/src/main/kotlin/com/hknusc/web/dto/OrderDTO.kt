package com.hknusc.web.dto

import java.sql.Timestamp

data class OrderDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var orderTime: Timestamp,
    var paymentTime: Timestamp,
    var reservationTime: Timestamp,
    var isServed: Boolean = false,
)

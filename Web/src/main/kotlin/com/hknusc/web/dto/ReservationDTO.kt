package com.hknusc.web.dto

import java.sql.Timestamp

data class ReservationDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var accountName: String,
    var reservationTime: Timestamp,
    var numberOfGuests: Int,
    var items: String?,
    var isCancelled: Boolean
)

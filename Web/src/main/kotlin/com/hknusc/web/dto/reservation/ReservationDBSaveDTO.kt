package com.hknusc.web.dto.reservation

import java.sql.Timestamp

data class ReservationDBSaveDTO(
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var isServed: Boolean,
    var isReservation: Boolean,
)

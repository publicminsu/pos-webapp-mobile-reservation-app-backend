package com.hknusc.web.dto.reservation

import java.sql.Timestamp

data class ReservationSaveDTO(
    var accountId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var isServed: Boolean = false,
    var isReservation: Boolean = true,
)

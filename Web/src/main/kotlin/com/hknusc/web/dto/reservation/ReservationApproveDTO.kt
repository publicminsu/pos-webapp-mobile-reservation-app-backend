package com.hknusc.web.dto.reservation

import java.sql.Timestamp

data class ReservationApproveDTO(
    var id: Int,
    var isReservation: Boolean,
    var reservationDenyDetail: String?
)

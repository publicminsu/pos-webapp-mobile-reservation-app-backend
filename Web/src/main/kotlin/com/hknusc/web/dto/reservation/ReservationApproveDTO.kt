package com.hknusc.web.dto.reservation

import com.hknusc.web.util.OrderCode
import java.sql.Timestamp

data class ReservationApproveDTO(
    var id: Int,
    var orderCode: OrderCode = OrderCode.RESERVATION,
    var reservationDenyDetail: String?
)

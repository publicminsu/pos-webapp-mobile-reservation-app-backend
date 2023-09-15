package com.hknusc.web.dto.reservation

import com.hknusc.web.util.type.OrderCode

data class ReservationApproveDTO(
    var id: Int,
    var orderCode: OrderCode = OrderCode.RESERVATION,
    var reservationDenyDetail: String?
)

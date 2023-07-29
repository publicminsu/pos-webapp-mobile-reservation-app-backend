package com.hknusc.web.dto.reservation

import com.hknusc.web.util.OrderCode

data class ReservationDBApproveDTO(
    var id: Int,
    var storeId: Int,
    var orderCode: OrderCode,
    var reservationDenyDetail: String?
)

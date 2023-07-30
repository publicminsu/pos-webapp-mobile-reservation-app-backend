package com.hknusc.web.dto.reservation

import com.hknusc.web.util.type.OrderCode

data class ReservationDBApproveDTO(
    var id: Int,
    var storeId: Int,
    var orderCode: OrderCode,
    var reservationDenyDetail: String?
)

package com.hknusc.web.dto.reservation

data class ReservationDBApproveDTO(
    var id: Int,
    var storeId: Int,
    var orderCode: Int,
    var reservationDenyDetail: String?
)

package com.hknusc.web.dto.reservation

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class ReservationEditDTO(
    var id: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode,
) {
    fun convertToReservationDB(accountId: Int) = ReservationDBEditDTO(
        id = id,
        accountId = accountId,
        tableId = tableId,
        reservationTime = reservationTime,
        orderCode = orderCode
    )
}

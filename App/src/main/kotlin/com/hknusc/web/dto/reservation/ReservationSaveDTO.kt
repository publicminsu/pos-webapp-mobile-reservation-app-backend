package com.hknusc.web.dto.reservation

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class ReservationSaveDTO(
    var storeId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode = OrderCode.RESERVATION_WAIT,
) {
    fun convertToReservationDB(accountId: Int) = ReservationDBSaveDTO(
        accountId = accountId,
        storeId = storeId,
        tableId = tableId,
        reservationTime = reservationTime,
        orderCode = orderCode
    )
}

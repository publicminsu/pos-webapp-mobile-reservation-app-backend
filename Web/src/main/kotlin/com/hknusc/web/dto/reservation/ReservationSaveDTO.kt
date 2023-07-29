package com.hknusc.web.dto.reservation

import com.hknusc.web.util.OrderCode
import java.sql.Timestamp

data class ReservationSaveDTO(
    var accountId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode = OrderCode.RESERVATION_WAIT,
)

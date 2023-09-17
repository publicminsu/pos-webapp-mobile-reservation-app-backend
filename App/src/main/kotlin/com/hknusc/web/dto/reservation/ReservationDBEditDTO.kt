package com.hknusc.web.dto.reservation

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class ReservationDBEditDTO(
    var id: Int,
    var accountId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode,
)

package com.hknusc.web.dto.reservation

import com.hknusc.web.util.OrderCode
import java.sql.Timestamp

data class ReservationDBSaveDTO(
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var reservationTime: Timestamp,
    var orderCode: OrderCode,
)

package com.hknusc.web.dto.order

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class OrderDBEditDTO(
    var id: Int,
    var accountId: Int,
    var tableId: Int,
    var orderTime: Timestamp?,
    var paymentTime: Timestamp?,
    var reservationTime: Timestamp?,
    var orderCode: OrderCode,
)

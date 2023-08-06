package com.hknusc.web.dto.order

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class OrderEditDTO(
    var id: Int,
    var tableId: Int,
    var orderTime: Timestamp?,
    var paymentTime: Timestamp?,
    var reservationTime: Timestamp?,
    var orderCode: OrderCode,
)

package com.hknusc.web.dto.order

import com.hknusc.web.util.OrderCode
import java.sql.Timestamp

data class OrderDBSaveDTO(
    var accountId: Int,
    var storeId: Int,
    var tableId: Int,
    var orderTime: Timestamp?,
    var paymentTime: Timestamp?,
    var reservationTime: Timestamp?,
    var orderCode: OrderCode,
    var reservationDenyDetail: String?
)

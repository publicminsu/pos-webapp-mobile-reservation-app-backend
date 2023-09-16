package com.hknusc.web.dto.order

import com.hknusc.web.util.type.OrderCode
import java.sql.Timestamp

data class OrderSaveDTO(
    val storeId: Int,
    val tableId: Int,
    val orderTime: Timestamp?,
    val paymentTime: Timestamp?,
    val reservationTime: Timestamp?,
    val orderCode: OrderCode = OrderCode.ORDER,
    val reservationDenyDetail: String?
) {
    fun convertToOrderDB(accountId: Int) = OrderDBSaveDTO(
        accountId = accountId,
        storeId = storeId,
        tableId = tableId,
        orderTime = orderTime,
        paymentTime = paymentTime,
        reservationTime = reservationTime,
        orderCode = orderCode,
        reservationDenyDetail = reservationDenyDetail
    )
}

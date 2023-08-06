package com.hknusc.web.dto.orderDetail

data class OrderDetailSaveDTO(
    var orderId: Int,
    var menuId: Int,
    var amount: Int,
)

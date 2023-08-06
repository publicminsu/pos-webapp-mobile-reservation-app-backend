package com.hknusc.web.dto.orderDetail

data class OrderDetailDTO(
    var id: Int,
    var orderId: Int,
    var menuId: Int,
    var amount: Int,
)

package com.hknusc.web.dto.order

data class OrderDetailDTO(
    var id: Int = 0,
    var orderId: Int,
    var menuId: Int,
    var amount: Int,
)

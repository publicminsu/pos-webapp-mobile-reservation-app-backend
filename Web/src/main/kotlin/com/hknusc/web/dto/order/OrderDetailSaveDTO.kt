package com.hknusc.web.dto.order

data class OrderDetailSaveDTO(
    var orderId: Int,
    var menuId: Int,
    var amount: Int,
)

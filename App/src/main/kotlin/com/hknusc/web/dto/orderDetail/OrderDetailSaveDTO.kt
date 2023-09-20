package com.hknusc.web.dto.orderDetail

import jakarta.validation.constraints.PositiveOrZero

data class OrderDetailSaveDTO(
    var orderId: Int,
    var menuId: Int,
    @field:PositiveOrZero
    var amount: Int,
)

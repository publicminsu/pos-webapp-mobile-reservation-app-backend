package com.hknusc.web.dto.orderDetail

import jakarta.validation.constraints.PositiveOrZero

data class OrderDetailEditDTO(
    var id: Int,
    var menuId: Int,
    @field:PositiveOrZero
    var amount: Int,
)

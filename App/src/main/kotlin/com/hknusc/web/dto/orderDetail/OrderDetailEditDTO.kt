package com.hknusc.web.dto.orderDetail

import jakarta.validation.constraints.PositiveOrZero

data class OrderDetailEditDTO(
    var id: Int,
    var menuId: Int,
    @PositiveOrZero
    var amount: Int,
)

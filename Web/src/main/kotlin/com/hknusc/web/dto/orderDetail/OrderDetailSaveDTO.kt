package com.hknusc.web.dto.orderDetail

import jakarta.validation.constraints.PositiveOrZero

data class OrderDetailSaveDTO(
    var orderId: Int,
    var menuId: Int,
    @PositiveOrZero
    var amount: Int,
)

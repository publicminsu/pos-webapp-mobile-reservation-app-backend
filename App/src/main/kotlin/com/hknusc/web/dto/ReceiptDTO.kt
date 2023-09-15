package com.hknusc.web.dto

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO

data class ReceiptDTO(
    val order: OrderDTO,
    val orderDetails: List<OrderDetailDTO>
)

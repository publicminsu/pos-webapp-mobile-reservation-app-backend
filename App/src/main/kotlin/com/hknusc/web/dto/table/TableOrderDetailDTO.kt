package com.hknusc.web.dto.table

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO

data class TableOrderDetailDTO(val table: TableDTO, val order: OrderDTO?, val orderDetails: List<OrderDetailDTO>)

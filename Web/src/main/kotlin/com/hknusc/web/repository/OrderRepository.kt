package com.hknusc.web.repository

import com.hknusc.web.dto.OrderDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OrderRepository {
    fun getOrders(): List<OrderDTO>
    fun getOrder(orderId: Int): OrderDTO?
    fun saveOrder(orderDTO: OrderDTO)
    fun editOrder(orderDTO: OrderDTO)
    fun deleteOrder(orderId: Int)
}
package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDBEditDTO
import com.hknusc.web.dto.order.OrderDBSaveDTO
import com.hknusc.web.dto.order.OrderDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OrderRepository {
    fun getOrders(storeId: Int): List<OrderDTO>
    fun getOrder(orderId: Int, storeId: Int): OrderDTO?
    fun saveOrder(orderDBSaveDTO: OrderDBSaveDTO)
    fun editOrder(orderDBEditDTO: OrderDBEditDTO): Int
    fun deleteOrder(orderId: Int, storeId: Int): Int
    fun getOrderByTableId(tableId: Int): OrderDTO?
}
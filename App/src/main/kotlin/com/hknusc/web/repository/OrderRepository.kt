package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDBEditDTO
import com.hknusc.web.dto.order.OrderDBSaveDTO
import com.hknusc.web.dto.order.OrderDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OrderRepository {
    fun getOrders(userId: Int): List<OrderDTO>
    fun getOrdersByStoreId(userId: Int, storeId: Int): List<OrderDTO>
    fun saveOrder(orderDBSaveDTO: OrderDBSaveDTO)
    fun editOrder(orderDBEditDTO: OrderDBEditDTO): Int
    fun deleteOrder(userId: Int, orderId: Int): Int
    fun getOrderByTableId(tableId: Int): OrderDTO?
    fun isNotEmptyTable(tableId: Int): Boolean
}

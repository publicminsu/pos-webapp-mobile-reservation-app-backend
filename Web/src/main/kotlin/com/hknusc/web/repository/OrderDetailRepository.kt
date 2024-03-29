package com.hknusc.web.repository

import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.dto.orderDetail.OrderDetailEditDTO
import com.hknusc.web.dto.orderDetail.OrderDetailSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OrderDetailRepository {
    fun getOrderDetails(orderId: Int): List<OrderDetailDTO>
    fun saveOrderDetail(orderDetailSaveDTO: OrderDetailSaveDTO)
    fun editOrderDetail(orderDetailEditDTO: OrderDetailEditDTO): Int
    fun deleteOrderDetail(orderDetailId: Int): Int
}
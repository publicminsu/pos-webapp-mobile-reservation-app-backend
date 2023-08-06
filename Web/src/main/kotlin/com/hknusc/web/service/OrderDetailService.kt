package com.hknusc.web.service

import com.hknusc.web.dto.orderDetail.OrderDetailSaveDTO
import com.hknusc.web.repository.OrderDetailRepository
import org.springframework.stereotype.Service

@Service
class OrderDetailService(private val orderDetailRepository: OrderDetailRepository) {
    fun getOrderDetails(orderId: Int) = orderDetailRepository.getOrderDetails(orderId)
    
    fun saveOrderDetail(orderDetailSaveDTO: OrderDetailSaveDTO) =
        orderDetailRepository.saveOrderDetail(orderDetailSaveDTO)
}
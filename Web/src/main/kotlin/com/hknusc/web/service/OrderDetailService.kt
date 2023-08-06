package com.hknusc.web.service

import com.hknusc.web.dto.orderDetail.OrderDetailSaveDTO
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class OrderDetailService(private val orderDetailRepository: OrderDetailRepository) {
    fun getOrderDetails(orderId: Int) = orderDetailRepository.getOrderDetails(orderId)
    
    fun saveOrderDetail(orderDetailSaveDTO: OrderDetailSaveDTO) =
        orderDetailRepository.saveOrderDetail(orderDetailSaveDTO)

    fun deleteOrderDetail(orderDetailId: Int) {
        if (orderDetailRepository.deleteOrderDetail(orderDetailId) == 0) {
            throw CustomException(ErrorCode.ORDER_DETAIL_NOT_FOUND)
        }
    }
}
package com.hknusc.web.service

import com.hknusc.web.dto.ReceiptDTO
import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.repository.ReceiptRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class ReceiptService(
    private val receiptRepository: ReceiptRepository,
    private val orderDetailRepository: OrderDetailRepository
) {
    fun getReceipt(userStoreId: Int, orderId: Int): ReceiptDTO {
        val orderDTO: OrderDTO
        try {
            orderDTO = receiptRepository.getReceipt(orderId, userStoreId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
        }
        val orderDetails: List<OrderDetailDTO> = orderDetailRepository.getOrderDetails(orderId)
        return ReceiptDTO(orderDTO, orderDetails)
    }
}

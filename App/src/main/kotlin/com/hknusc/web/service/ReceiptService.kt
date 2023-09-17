package com.hknusc.web.service

import com.hknusc.web.dto.ReceiptDTO
import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.repository.ReceiptRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReceiptService(
    private val tokenProvider: JwtTokenProvider,
    private val receiptRepository: ReceiptRepository,
    private val orderDetailRepository: OrderDetailRepository
) {
    fun getReceipts(bearerAccessToken: String): List<ReceiptDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)
        val orders = receiptRepository.getReceipts(userId)

        val receipts: MutableList<ReceiptDTO> = mutableListOf()

        orders.forEach {
            val orderDetails: List<OrderDetailDTO> = orderDetailRepository.getOrderDetails(it.id)
            val receipt = ReceiptDTO(it, orderDetails)
            receipts.add(receipt)
        }
        return receipts
    }
}
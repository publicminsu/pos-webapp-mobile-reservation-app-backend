package com.hknusc.web.service

import com.hknusc.web.dto.order.*
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class OrderService(private val tokenProvider: JwtTokenProvider, private val orderRepository: OrderRepository) {
    fun getOrders(bearerAccessToken: String): List<OrderDTO> {
        
        return orderRepository.getOrders(storeId)
    }

    fun getOrder(storeId: Int, orderId: Int): OrderDTO {
        try {
            return orderRepository.getOrder(orderId, storeId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
        }
    }

    fun saveOrder(storeId: Int, orderSaveDTO: OrderSaveDTO) {
        if (orderRepository.isNotEmptyTable(orderSaveDTO.tableId))
            throw CustomException(ErrorCode.TABLE_NOT_EMPTY)

        val orderDBSaveDTO = OrderDBSaveDTO(
            orderSaveDTO.accountId,
            storeId,
            orderSaveDTO.tableId,
            orderSaveDTO.orderTime,
            orderSaveDTO.paymentTime,
            orderSaveDTO.reservationTime,
            orderSaveDTO.orderCode,
            orderSaveDTO.reservationDenyDetail
        )
        orderRepository.saveOrder(orderDBSaveDTO)
    }

    fun editOrder(storeId: Int, orderEditDTO: OrderEditDTO) {
        val orderDBEditDTO = OrderDBEditDTO(
            orderEditDTO.id,
            storeId,
            orderEditDTO.tableId,
            orderEditDTO.orderTime,
            orderEditDTO.paymentTime,
            orderEditDTO.reservationTime,
            orderEditDTO.orderCode
        )

        if (orderRepository.editOrder(orderDBEditDTO) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }

    fun deleteOrder(storeId: Int, orderId: Int) {
        if (orderRepository.deleteOrder(orderId, storeId) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }
}

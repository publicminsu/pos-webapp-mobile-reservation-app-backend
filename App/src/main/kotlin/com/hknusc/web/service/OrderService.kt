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
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        return orderRepository.getOrders(userStoreId)
    }

    fun getOrder(bearerAccessToken: String, orderId: Int): OrderDTO {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        try {
            return orderRepository.getOrder(orderId, userStoreId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
        }
    }

    fun saveOrder(bearerAccessToken: String, orderSaveDTO: OrderSaveDTO) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        if (orderRepository.isNotEmptyTable(orderSaveDTO.tableId))
            throw CustomException(ErrorCode.TABLE_NOT_EMPTY)

        val orderDBSaveDTO = OrderDBSaveDTO(
            orderSaveDTO.accountId,
            userStoreId,
            orderSaveDTO.tableId,
            orderSaveDTO.orderTime,
            orderSaveDTO.paymentTime,
            orderSaveDTO.reservationTime,
            orderSaveDTO.orderCode,
            orderSaveDTO.reservationDenyDetail
        )
        orderRepository.saveOrder(orderDBSaveDTO)
    }

    fun editOrder(bearerAccessToken: String, orderEditDTO: OrderEditDTO) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        val orderDBEditDTO = OrderDBEditDTO(
            orderEditDTO.id,
            userStoreId,
            orderEditDTO.tableId,
            orderEditDTO.orderTime,
            orderEditDTO.paymentTime,
            orderEditDTO.reservationTime,
            orderEditDTO.orderCode
        )

        if (orderRepository.editOrder(orderDBEditDTO) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }

    fun deleteOrder(bearerAccessToken: String, orderId: Int) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        if (orderRepository.deleteOrder(orderId, userStoreId) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }
}

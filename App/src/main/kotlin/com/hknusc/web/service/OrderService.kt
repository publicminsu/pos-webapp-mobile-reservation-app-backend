package com.hknusc.web.service

import com.hknusc.web.dto.order.*
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class OrderService(private val tokenProvider: JWTTokenProvider, private val orderRepository: OrderRepository) {
    fun getOrders(bearerAccessToken: String): List<OrderDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        return orderRepository.getOrders(userId)
    }

    fun getOrdersByStoreId(bearerAccessToken: String, storeId: Int): List<OrderDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        return orderRepository.getOrdersByStoreId(userId, storeId)
    }

    fun saveOrder(bearerAccessToken: String, orderSaveDTO: OrderSaveDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        if (orderRepository.isNotEmptyTable(orderSaveDTO.tableId))
            throw CustomException(ErrorCode.TABLE_NOT_EMPTY)

        val orderDBSaveDTO = orderSaveDTO.convertToOrderDB(userId)
        orderRepository.saveOrder(orderDBSaveDTO)
    }

    fun editOrder(bearerAccessToken: String, orderEditDTO: OrderEditDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)
        val orderDBEditDTO = orderEditDTO.convertToOrderDB(userId)

        if (orderRepository.editOrder(orderDBEditDTO) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }

    fun deleteOrder(bearerAccessToken: String, orderId: Int) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        if (orderRepository.deleteOrder(userId, orderId) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }
}

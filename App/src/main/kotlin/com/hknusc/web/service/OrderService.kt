package com.hknusc.web.service

import com.hknusc.web.dto.order.*
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class OrderService(private val tokenProvider: JWTTokenProvider, private val orderRepository: OrderRepository) {
    fun getOrders(userId: Int) = orderRepository.getOrders(userId)

    fun getOrdersByStoreId(userId: Int, storeId: Int) = orderRepository.getOrdersByStoreId(userId, storeId)

    fun saveOrder(userId: Int, orderSaveDTO: OrderSaveDTO) {
        if (orderRepository.isNotEmptyTable(orderSaveDTO.tableId))
            throw CustomException(ErrorCode.TABLE_NOT_EMPTY)

        val orderDBSaveDTO = orderSaveDTO.convertToOrderDB(userId)
        orderRepository.saveOrder(orderDBSaveDTO)
    }

    fun editOrder(userId: Int, orderEditDTO: OrderEditDTO) {
        val orderDBEditDTO = orderEditDTO.convertToOrderDB(userId)

        if (orderRepository.editOrder(orderDBEditDTO) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }

    fun deleteOrder(userId: Int, orderId: Int) {
        if (orderRepository.deleteOrder(userId, orderId) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }
}

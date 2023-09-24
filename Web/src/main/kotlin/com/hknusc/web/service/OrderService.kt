package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDBEditDTO
import com.hknusc.web.dto.order.OrderDBSaveDTO
import com.hknusc.web.dto.order.OrderEditDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun getOrders(userStoreId: Int) = orderRepository.getOrders(userStoreId)

    fun getOrder(userStoreId: Int, orderId: Int) = try {
        orderRepository.getOrder(orderId, userStoreId)!!
    } catch (e: Exception) {
        throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }


    fun saveOrder(userStoreId: Int, orderSaveDTO: OrderSaveDTO) {
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

    fun editOrder(userStoreId: Int, orderEditDTO: OrderEditDTO) {
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

    fun deleteOrder(userStoreId: Int, orderId: Int) {
        if (orderRepository.deleteOrder(orderId, userStoreId) == 0)
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
    }
}

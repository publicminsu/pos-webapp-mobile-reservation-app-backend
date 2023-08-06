package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDBSaveDTO
import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class OrderService(private val tokenProvider: JwtTokenProvider, private val orderRepository: OrderRepository) {
    fun getOrders(bearerAccessToken: String): List<OrderDTO> {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        return orderRepository.getOrders(userStoreId)
    }

    fun getOrder(bearerAccessToken: String, orderId: Int): OrderDTO {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        try {
            return orderRepository.getOrder(orderId, userStoreId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.ORDER_NOT_FOUND)
        }
    }

    fun saveOrder(bearerAccessToken: String, orderSaveDTO: OrderSaveDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

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

    fun editOrder(orderDTO: OrderDTO) = orderRepository.editOrder(orderDTO)
    fun deleteOrder(orderId: Int) = orderRepository.deleteOrder(orderId)
}
package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDBSaveDTO
import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class OrderService(private val tokenProvider: JwtTokenProvider, private val orderRepository: OrderRepository) {
    fun getOrders() = orderRepository.getOrders()
    fun getOrder(orderId: Int) = orderRepository.getOrder(orderId)
    fun saveOrder(bearerAccessToken: String, orderSaveDTO: OrderSaveDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)
        
        val orderDBSaveDTO = OrderDBSaveDTO(
            orderSaveDTO.accountId,
            userStoreId,
            orderSaveDTO.tableId,
            orderSaveDTO.orderTime,
            orderSaveDTO.paymentTime,
            orderSaveDTO.reservationTime,
            orderSaveDTO.isServed,
            orderSaveDTO.isReservation,
            orderSaveDTO.reservationDenyDetail
        )
        orderRepository.saveOrder(orderDBSaveDTO)
    }

    fun editOrder(orderDTO: OrderDTO) = orderRepository.editOrder(orderDTO)
    fun deleteOrder(orderId: Int) = orderRepository.deleteOrder(orderId)
}
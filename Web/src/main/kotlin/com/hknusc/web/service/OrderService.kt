package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun getOrders() = orderRepository.getOrders()
    fun getOrder(orderId: Int) = orderRepository.getOrder(orderId)
    fun saveOrder(orderDTO: OrderDTO) = orderRepository.saveOrder(orderDTO)
    fun editOrder(orderDTO: OrderDTO) = orderRepository.editOrder(orderDTO)
    fun deleteOrder(orderId: Int) = orderRepository.deleteOrder(orderId)
}
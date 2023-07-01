package com.hknusc.web.service

import com.hknusc.web.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
}
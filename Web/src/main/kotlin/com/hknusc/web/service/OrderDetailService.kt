package com.hknusc.web.service

import com.hknusc.web.repository.OrderDetailRepository
import org.springframework.stereotype.Service

@Service
class OrderDetailService(private val orderDetailRepository: OrderDetailRepository) {
}
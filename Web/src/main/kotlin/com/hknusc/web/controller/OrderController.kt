package com.hknusc.web.controller

import com.hknusc.web.service.OrderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
}
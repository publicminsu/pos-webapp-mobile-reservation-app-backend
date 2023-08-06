package com.hknusc.web.controller

import com.hknusc.web.service.OrderDetailService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders/detail")
class OrderDetailController(private val orderDetailService: OrderDetailService) {
}
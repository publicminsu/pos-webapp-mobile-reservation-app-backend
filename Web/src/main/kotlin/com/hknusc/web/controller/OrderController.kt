package com.hknusc.web.controller

import com.hknusc.web.dto.OrderDTO
import com.hknusc.web.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
    @GetMapping
    fun getOrders() = orderService.getOrders()

    @GetMapping("{orderId}")
    fun getOrder(@PathVariable("orderId") orderId: Int) = orderService.getOrder(orderId)

    @PostMapping
    fun saveOrder(orderDTO: OrderDTO) = orderService.saveOrder(orderDTO)

    @PatchMapping
    fun editOrder(orderDTO: OrderDTO) = orderService.editOrder(orderDTO)

    @DeleteMapping("{orderId}")
    fun deleteOrder(@PathVariable("orderId") orderId: Int) = orderService.deleteOrder(orderId)
}
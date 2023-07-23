package com.hknusc.web.controller

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.service.OrderService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
    @GetMapping
    fun getOrders() = orderService.getOrders()

    @GetMapping("{orderId}")
    fun getOrder(@PathVariable("orderId") orderId: Int) = orderService.getOrder(orderId)

    @PostMapping
    fun saveOrder(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, orderSaveDTO: OrderSaveDTO) =
        orderService.saveOrder(accessToken, orderSaveDTO)

    @PatchMapping
    fun editOrder(orderDTO: OrderDTO) = orderService.editOrder(orderDTO)

    @DeleteMapping("{orderId}")
    fun deleteOrder(@PathVariable("orderId") orderId: Int) = orderService.deleteOrder(orderId)
}
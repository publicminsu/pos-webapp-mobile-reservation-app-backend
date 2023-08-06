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
    fun getOrders(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = orderService.getOrders(accessToken)

    @GetMapping("{orderId}")
    fun getOrder(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("orderId") orderId: Int
    ) = orderService.getOrder(accessToken, orderId)

    @PostMapping
    fun saveOrder(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, orderSaveDTO: OrderSaveDTO) =
        orderService.saveOrder(accessToken, orderSaveDTO)

    @PatchMapping
    fun editOrder(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, orderDTO: OrderDTO) =
        orderService.editOrder(accessToken, orderDTO)

    @DeleteMapping("{orderId}")
    fun deleteOrder(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("orderId") orderId: Int
    ) = orderService.deleteOrder(accessToken, orderId)
}
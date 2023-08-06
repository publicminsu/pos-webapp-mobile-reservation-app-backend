package com.hknusc.web.controller

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.order.OrderEditDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.service.OrderService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
    //주문 내역 가져오기
    @GetMapping
    fun getOrders(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = orderService.getOrders(accessToken)

    //특정 주문 내역 가져오기
    @GetMapping("{orderId}")
    fun getOrder(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("orderId") orderId: Int
    ) = orderService.getOrder(accessToken, orderId)

    //주문 내역 저장
    @PostMapping
    fun saveOrder(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, orderSaveDTO: OrderSaveDTO) =
        orderService.saveOrder(accessToken, orderSaveDTO)

    //주문 내역 수정
    @PatchMapping
    fun editOrder(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, orderEditDTO: OrderEditDTO) =
        orderService.editOrder(accessToken, orderEditDTO)

    //주문 내역 삭제
    @DeleteMapping("{orderId}")
    fun deleteOrder(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("orderId") orderId: Int
    ) = orderService.deleteOrder(accessToken, orderId)
}
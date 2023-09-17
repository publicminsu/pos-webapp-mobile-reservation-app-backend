package com.hknusc.web.controller

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
    fun getOrders(@RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String) = orderService.getOrders(accessToken)

    //특정 주문 내역 가져오기
    @GetMapping("{storeId}")
    fun getOrdersByStoreId(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable storeId: Int
    ) = orderService.getOrdersByStoreId(accessToken, storeId)

    //주문 내역 저장
    @PostMapping
    fun saveOrder(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        orderSaveDTO: OrderSaveDTO
    ) =
        orderService.saveOrder(accessToken, orderSaveDTO)

    //주문 내역 수정
    @PatchMapping
    fun editOrder(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        orderEditDTO: OrderEditDTO
    ) =
        orderService.editOrder(accessToken, orderEditDTO)

    //주문 내역 삭제
    @DeleteMapping("{orderId}")
    fun deleteOrder(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable orderId: Int
    ) = orderService.deleteOrder(accessToken, orderId)
}
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
    @GetMapping("{storeId}/{orderId}")
    fun getOrder(
        @PathVariable storeId: Int,
        @PathVariable orderId: Int
    ) = orderService.getOrder(storeId, orderId)

    //주문 내역 저장
    @PostMapping("{storeId}")
    fun saveOrder(@PathVariable storeId: Int, orderSaveDTO: OrderSaveDTO) =
        orderService.saveOrder(storeId, orderSaveDTO)

    //주문 내역 수정
    @PatchMapping("{storeId}")
    fun editOrder(@PathVariable storeId: Int, orderEditDTO: OrderEditDTO) =
        orderService.editOrder(storeId, orderEditDTO)

    //주문 내역 삭제
    @DeleteMapping("{storeId}/{orderId}")
    fun deleteOrder(
        @PathVariable storeId: Int,
        @PathVariable orderId: Int
    ) = orderService.deleteOrder(storeId, orderId)
}

package com.hknusc.web.controller

import com.hknusc.web.dto.order.OrderEditDTO
import com.hknusc.web.dto.order.OrderSaveDTO
import com.hknusc.web.service.OrderService
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
    //주문 내역 가져오기
    @GetMapping
    fun getOrders(@RequestAttribute userId: Int) = orderService.getOrders(userId)

    //특정 주문 내역 가져오기
    @GetMapping("{storeId}")
    fun getOrdersByStoreId(
        @RequestAttribute userId: Int,
        @PathVariable storeId: Int
    ) = orderService.getOrdersByStoreId(userId, storeId)

    //주문 내역 저장
    @PostMapping
    fun saveOrder(
        @RequestAttribute userId: Int,
        orderSaveDTO: OrderSaveDTO
    ) =
        orderService.saveOrder(userId, orderSaveDTO)

    //주문 내역 수정
    @PatchMapping
    fun editOrder(
        @RequestAttribute userId: Int,
        orderEditDTO: OrderEditDTO
    ) =
        orderService.editOrder(userId, orderEditDTO)

    //주문 내역 삭제
    @DeleteMapping("{orderId}")
    fun deleteOrder(
        @RequestAttribute userId: Int,
        @PathVariable orderId: Int
    ) = orderService.deleteOrder(userId, orderId)
}

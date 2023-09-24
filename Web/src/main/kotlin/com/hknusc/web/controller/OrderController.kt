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
    fun getOrders(@RequestAttribute userStoreId: Int) = orderService.getOrders(userStoreId)

    //특정 주문 내역 가져오기
    @GetMapping("{orderId}")
    fun getOrder(
        @RequestAttribute userStoreId: Int,
        @PathVariable("orderId") orderId: Int
    ) = orderService.getOrder(userStoreId, orderId)

    //주문 내역 저장
    @PostMapping
    fun saveOrder(@RequestAttribute userStoreId: Int, orderSaveDTO: OrderSaveDTO) =
        orderService.saveOrder(userStoreId, orderSaveDTO)

    //주문 내역 수정
    @PatchMapping
    fun editOrder(@RequestAttribute userStoreId: Int, orderEditDTO: OrderEditDTO) =
        orderService.editOrder(userStoreId, orderEditDTO)

    //주문 내역 삭제
    @DeleteMapping("{orderId}")
    fun deleteOrder(
        @RequestAttribute userStoreId: Int,
        @PathVariable("orderId") orderId: Int
    ) = orderService.deleteOrder(userStoreId, orderId)
}

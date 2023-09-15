package com.hknusc.web.controller

import com.hknusc.web.dto.orderDetail.OrderDetailEditDTO
import com.hknusc.web.dto.orderDetail.OrderDetailSaveDTO
import com.hknusc.web.service.OrderDetailService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders/details")
class OrderDetailController(private val orderDetailService: OrderDetailService) {
    //주문 내역 ID 기준으로 상세 주문 내역 가져오기
    @GetMapping("{orderId}")
    fun getOrderDetails(@PathVariable("orderId") orderId: Int) = orderDetailService.getOrderDetails(orderId)

    //상세 주문 내역 저장하기
    @PostMapping
    fun saveOrderDetail(@Valid orderDetailSaveDTO: OrderDetailSaveDTO) =
        orderDetailService.saveOrderDetail(orderDetailSaveDTO)

    //상세 주문 내역 수정하기
    @PatchMapping
    fun editOrderDetail(@Valid orderDetailEditDTO: OrderDetailEditDTO) =
        orderDetailService.editOrderDetail(orderDetailEditDTO)

    //상세 주문 내역 삭제하기
    @DeleteMapping("{orderDetailId}")
    fun deleteOrderDetail(@PathVariable("orderDetailId") orderDetailId: Int) =
        orderDetailService.deleteOrderDetail(orderDetailId)
}

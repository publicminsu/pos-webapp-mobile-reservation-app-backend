package com.hknusc.web.controller

import com.hknusc.web.dto.orderDetail.OrderDetailSaveDTO
import com.hknusc.web.service.OrderDetailService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders/details")
class OrderDetailController(private val orderDetailService: OrderDetailService) {
    @GetMapping("{orderId}")
    fun getOrderDetails(@PathVariable("orderId") orderId: Int) = orderDetailService.getOrderDetails(orderId)

    @PostMapping
    fun saveOrderDetail(orderDetailSaveDTO: OrderDetailSaveDTO) = orderDetailService.saveOrderDetail(orderDetailSaveDTO)

    @DeleteMapping("{orderDetailId}")
    fun deleteOrderDetail(@PathVariable("orderDetailId") orderDetailId: Int) =
        orderDetailService.deleteOrderDetail(orderDetailId)
}
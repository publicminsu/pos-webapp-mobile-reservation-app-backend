package com.hknusc.web.controller

import com.hknusc.web.service.ReceiptService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("receipts")
class ReceiptController(private val receiptService: ReceiptService) {
    @GetMapping("{orderId}")
    fun getReceipt(
        @RequestAttribute userStoreId: Int,
        @PathVariable("orderId") orderId: Int
    ) = receiptService.getReceipt(userStoreId, orderId)
}

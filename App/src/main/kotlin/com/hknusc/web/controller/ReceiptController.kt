package com.hknusc.web.controller

import com.hknusc.web.service.ReceiptService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("receipts")
class ReceiptController(private val receiptService: ReceiptService) {
    @GetMapping("{orderId}")
    fun getReceipt(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable("orderId") orderId: Int
    ) = receiptService.getReceipt(accessToken, orderId)
}

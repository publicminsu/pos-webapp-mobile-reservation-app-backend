package com.hknusc.web.controller

import com.hknusc.web.service.ReceiptService
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("receipts")
class ReceiptController(private val receiptService: ReceiptService) {
    //사용자의 모든 결제 상태 주문 가져오기
    @GetMapping
    fun getReceipt(
        @RequestAttribute userId: Int
    ) = receiptService.getReceipts(userId)
}

package com.hknusc.web.controller

import com.hknusc.web.service.ReceiptService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("receipts")
class ReceiptController(private val receiptService: ReceiptService)
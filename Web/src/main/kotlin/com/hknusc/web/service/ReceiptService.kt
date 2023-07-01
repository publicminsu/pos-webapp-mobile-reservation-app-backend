package com.hknusc.web.service

import com.hknusc.web.repository.ReceiptRepository
import org.springframework.stereotype.Service

@Service
class ReceiptService(private val receiptRepository: ReceiptRepository) {
}
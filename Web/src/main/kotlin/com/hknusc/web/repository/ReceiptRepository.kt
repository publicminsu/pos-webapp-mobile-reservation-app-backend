package com.hknusc.web.repository

import com.hknusc.web.dto.order.OrderDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReceiptRepository {
    fun getReceipt(orderId: Int, storeId: Int): OrderDTO?
}
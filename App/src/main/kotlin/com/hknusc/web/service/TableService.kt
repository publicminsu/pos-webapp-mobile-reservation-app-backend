package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.dto.table.TableOrderDetailDTO
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.repository.TableRepository
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class TableService(
    private val tokenProvider: JWTTokenProvider,
    private val tableRepository: TableRepository,
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository
) {
    fun getTables(storeId: Int) = tableRepository.getTables(storeId)

    fun getTablesOrdersDetails(storeId: Int): List<TableOrderDetailDTO> {
        val tablesOrdersDetails: MutableList<TableOrderDetailDTO> = mutableListOf()

        val tables = tableRepository.getTables(storeId)

        for (table in tables) {

            val order: OrderDTO? = orderRepository.getOrderByTableId(table.id)

            val orderDetails: List<OrderDetailDTO> = if (order == null) {
                emptyList()
            } else {
                orderDetailRepository.getOrderDetails(order.id)
            }

            tablesOrdersDetails.add(TableOrderDetailDTO(table, order, orderDetails))
        }

        return tablesOrdersDetails
    }
}

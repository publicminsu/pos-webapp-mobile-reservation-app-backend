package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableListDBSaveDTO
import com.hknusc.web.dto.table.TableListSaveDTO
import com.hknusc.web.dto.table.TableOrderDetailDTO
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.repository.TableRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class TableService(
    private val tokenProvider: JWTTokenProvider,
    private val tableRepository: TableRepository,
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository
) {
    fun getTables(bearerAccessToken: String): List<TableDTO> {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        return tableRepository.getTables(userStoreId)
    }

    fun getTable(bearerAccessToken: String, tableId: Int): TableDTO {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        try {
            return tableRepository.getTable(tableId, userStoreId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.TABLE_NOT_FOUND)
        }
    }

    fun saveTable(bearerAccessToken: String, tableListSaveDTO: TableListSaveDTO) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        tableRepository.deleteTable(userStoreId)

        if (tableListSaveDTO.tableList == null)
            return

        val tableListDBSaveDTO = TableListDBSaveDTO(
            userStoreId,
            tableListSaveDTO.tableList!!
        )

        tableRepository.saveTable(tableListDBSaveDTO)
    }

    fun getTablesOrdersDetails(bearerAccessToken: String): List<TableOrderDetailDTO> {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        val tablesOrdersDetails: MutableList<TableOrderDetailDTO> = mutableListOf()

        val tables = tableRepository.getTables(userStoreId)

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

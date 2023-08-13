package com.hknusc.web.service

import com.hknusc.web.dto.order.OrderDTO
import com.hknusc.web.dto.orderDetail.OrderDetailDTO
import com.hknusc.web.dto.table.*
import com.hknusc.web.repository.OrderDetailRepository
import com.hknusc.web.repository.OrderRepository
import com.hknusc.web.repository.TableRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestHeader

@Service
class TableService(
    private val tokenProvider: JwtTokenProvider,
    private val tableRepository: TableRepository,
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository
) {
    fun getTables(bearerAccessToken: String): List<TableDTO> {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        return tableRepository.getTables(userStoreId)
    }

    fun getTable(bearerAccessToken: String, tableId: Int): TableDTO {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        try {
            return tableRepository.getTable(tableId, userStoreId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.TABLE_NOT_FOUND)
        }
    }

    fun saveTable(bearerAccessToken: String, tableSaveDTO: TableSaveDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        val tableDBSaveDTO = TableDBSaveDTO(
            userStoreId,
            tableSaveDTO.name,
            tableSaveDTO.coordX,
            tableSaveDTO.coordY,
            tableSaveDTO.width,
            tableSaveDTO.height,
            tableSaveDTO.privateKey
        )

        tableRepository.saveTable(tableDBSaveDTO)
    }

    fun editTable(bearerAccessToken: String, tableEditDTO: TableEditDTO) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        val tableDTO = TableDTO(
            tableEditDTO.id,
            userStoreId,
            tableEditDTO.name,
            tableEditDTO.coordX,
            tableEditDTO.coordY,
            tableEditDTO.width,
            tableEditDTO.height,
            tableEditDTO.privateKey
        )

        if (tableRepository.editTable(tableDTO) == 0) {
            throw CustomException(ErrorCode.TABLE_NOT_FOUND)
        }
    }

    fun deleteTable(bearerAccessToken: String, tableId: Int) {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

        if (tableRepository.deleteTable(tableId, userStoreId) == 0) {
            throw CustomException(ErrorCode.TABLE_NOT_FOUND)
        }
    }

    fun getTablesOrdersDetails(bearerAccessToken: String): List<TableOrderDetailDTO> {
        val userStoreId = tokenProvider.getUserStoreIdByBearerAccessToken(bearerAccessToken)

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
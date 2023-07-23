package com.hknusc.web.service

import com.hknusc.web.dto.table.TableDBSaveDTO
import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableEditDTO
import com.hknusc.web.dto.table.TableSaveDTO
import com.hknusc.web.repository.TableRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class TableService(private val tokenProvider: JwtTokenProvider, private val tableRepository: TableRepository) {
    fun getTables(bearerAccessToken: String): List<TableDTO> {
        val userStoreId = getUserStoreId(bearerAccessToken)

        return tableRepository.getTables(userStoreId)
    }

    fun getTable(bearerAccessToken: String, tableId: Int): TableDTO {
        val userStoreId = getUserStoreId(bearerAccessToken)

        try {
            return tableRepository.getTable(tableId, userStoreId)!!
        } catch (_: Exception) {
            throw CustomException(ErrorCode.TABLE_NOT_FOUND)
        }
    }

    fun saveTable(bearerAccessToken: String, tableSaveDTO: TableSaveDTO) {
        val userStoreId = getUserStoreId(bearerAccessToken)

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
        val userStoreId = getUserStoreId(bearerAccessToken)

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

        tableRepository.editTable(tableDTO)
    }

    fun deleteTable(bearerAccessToken: String, tableId: Int) = tableRepository.deleteTable(tableId)
    private fun getUserStoreId(bearerAccessToken: String): Int {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        return tokenProvider.findUserStoreIdByClaims(claims).toInt()
    }
}
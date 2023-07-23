package com.hknusc.web.service

import com.hknusc.web.dto.table.TableDBSaveDTO
import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableSaveDTO
import com.hknusc.web.repository.TableRepository
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class TableService(private val tokenProvider: JwtTokenProvider, private val tableRepository: TableRepository) {
    fun getTables() = tableRepository.getTables()
    fun getTable(tableId: Int) = tableRepository.getTable(tableId)
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

    fun editTable(tableDTO: TableDTO) = tableRepository.editTable(tableDTO)
    fun deleteTable(tableId: Int) = tableRepository.deleteTable(tableId)
    private fun getUserStoreId(bearerAccessToken: String): Int {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        return tokenProvider.findUserStoreIdByClaims(claims).toInt()
    }
}
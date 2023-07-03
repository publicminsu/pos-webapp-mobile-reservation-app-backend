package com.hknusc.web.service

import com.hknusc.web.dto.TableDTO
import com.hknusc.web.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class TableService(private val tableRepository: TableRepository) {
    fun getTables() = tableRepository.getTables()
    fun getTable(tableId: Int) = tableRepository.getTable(tableId)
    fun saveTable(tableDTO: TableDTO) = tableRepository.saveTable(tableDTO)
    fun editTable(tableDTO: TableDTO) = tableRepository.editTable(tableDTO)
    fun deleteTable(tableId: Int) = tableRepository.deleteTable(tableId)
}
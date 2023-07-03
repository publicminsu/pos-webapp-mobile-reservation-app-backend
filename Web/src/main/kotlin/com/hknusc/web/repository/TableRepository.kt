package com.hknusc.web.repository

import com.hknusc.web.dto.TableDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TableRepository {
    fun getTables(): List<TableDTO>
    fun getTable(tableId: Int): TableDTO
    fun saveTable(tableDTO: TableDTO)
    fun editTable(tableDTO: TableDTO)
    fun deleteTable(tableId: Int)
}
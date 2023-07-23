package com.hknusc.web.repository

import com.hknusc.web.dto.table.TableDBSaveDTO
import com.hknusc.web.dto.table.TableDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TableRepository {
    fun getTables(): List<TableDTO>
    fun getTable(tableId: Int): TableDTO
    fun saveTable(tableDBSaveDTO: TableDBSaveDTO)
    fun editTable(tableDTO: TableDTO)
    fun deleteTable(tableId: Int)
}
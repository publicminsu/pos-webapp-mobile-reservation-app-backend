package com.hknusc.web.repository

import com.hknusc.web.dto.table.TableDBSaveDTO
import com.hknusc.web.dto.table.TableDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TableRepository {
    fun getTables(storeId: Int): List<TableDTO>
    fun getTable(tableId: Int, storeId: Int): TableDTO?
    fun saveTable(tableDBSaveDTO: TableDBSaveDTO)
    fun editTable(tableDTO: TableDTO)
    fun deleteTable(tableId: Int)
}
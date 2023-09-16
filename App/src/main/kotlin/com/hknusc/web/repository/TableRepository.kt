package com.hknusc.web.repository

import com.hknusc.web.dto.table.TableDTO
import com.hknusc.web.dto.table.TableListDBSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TableRepository {
    fun getTables(storeId: Int): List<TableDTO>
}

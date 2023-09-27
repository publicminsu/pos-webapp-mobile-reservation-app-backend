package com.hknusc.web.repository

import com.hknusc.web.dto.store.OperatingDayDTO
import com.hknusc.web.util.type.OperatingDay
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OperatingDayRepository {
    fun getDays(userStoreId: Int): List<OperatingDay>?
    fun saveDays(operatingDayDTO: OperatingDayDTO)
    fun clearDays(userStoreId: Int)
}

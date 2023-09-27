package com.hknusc.web.repository

import com.hknusc.web.util.type.OperatingDay
import org.apache.ibatis.annotations.Mapper

@Mapper
interface OperatingDayRepository {
    fun getDays(userStoreId: Int): List<OperatingDay>?
}

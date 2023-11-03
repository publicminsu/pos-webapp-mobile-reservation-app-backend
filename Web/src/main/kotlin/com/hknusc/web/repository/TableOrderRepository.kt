package com.hknusc.web.repository

import com.hknusc.web.dto.menu.MenuDTO
import com.hknusc.web.dto.store.DistanceDTO
import com.hknusc.web.dto.store.DistanceStoreDBDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TableOrderRepository {
    fun getStoresByCoordinate(distanceDTO: DistanceDTO): List<DistanceStoreDBDTO>
    fun getMenusByTableId(tableId: Int): List<MenuDTO>
}

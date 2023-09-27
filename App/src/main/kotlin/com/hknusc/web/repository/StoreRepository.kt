package com.hknusc.web.repository

import com.hknusc.web.dto.store.DistanceDTO
import com.hknusc.web.dto.store.DistanceStoreDBDTO
import com.hknusc.web.dto.store.StoreDBDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStoresByCoordinate(distanceDTO: DistanceDTO): List<DistanceStoreDBDTO>
    fun getStore(storeId: Int): StoreDBDTO?
}

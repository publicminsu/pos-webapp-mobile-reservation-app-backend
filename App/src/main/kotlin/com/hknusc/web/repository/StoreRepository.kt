package com.hknusc.web.repository

import com.hknusc.web.dto.store.DistanceStoreDTO
import com.hknusc.web.dto.store.StoreDBDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double): List<DistanceStoreDTO>
    fun getStore(storeId: Int): StoreDBDTO
}

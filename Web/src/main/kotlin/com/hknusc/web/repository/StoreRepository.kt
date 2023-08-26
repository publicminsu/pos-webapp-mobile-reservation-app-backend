package com.hknusc.web.repository

import com.hknusc.web.dto.store.DistanceStoreDTO
import com.hknusc.web.dto.store.StoreDBSaveDTO
import com.hknusc.web.dto.store.StoreDTO
import com.hknusc.web.dto.store.StoreOpenDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStores(userId: Int): List<StoreDTO>
    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double): List<DistanceStoreDTO>
    fun saveStore(storeDBSaveDTO: StoreDBSaveDTO)
    fun setOpen(userId: Int, storeOpenDTO: StoreOpenDTO): Int
}

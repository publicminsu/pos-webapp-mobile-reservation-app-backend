package com.hknusc.web.repository

import com.hknusc.web.dto.store.*
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStores(userId: Int): List<StoreDBDTO>
    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double): List<DistanceStoreDTO>
    fun saveStore(storeDBSaveDTO: StoreDBSaveDTO)
    fun editStore(storeDBEditDTO: StoreDBEditDTO)
    fun setOpen(userId: Int, storeOpenDTO: StoreOpenDTO): Int
}

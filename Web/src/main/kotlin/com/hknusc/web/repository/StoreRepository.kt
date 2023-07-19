package com.hknusc.web.repository

import com.hknusc.web.dto.store.StoreDBSaveDTO
import com.hknusc.web.dto.store.StoreDTO
import com.hknusc.web.dto.store.StoreOpenDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStores(userId: Int): List<StoreDTO>
    fun saveStore(storeDBSaveDTO: StoreDBSaveDTO)
    fun setOpen(userId: Int, storeOpenDTO: StoreOpenDTO): Int
}
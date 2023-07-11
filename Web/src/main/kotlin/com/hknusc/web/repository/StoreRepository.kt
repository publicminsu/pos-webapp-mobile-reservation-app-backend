package com.hknusc.web.repository

import com.hknusc.web.dto.StoreDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface StoreRepository {
    fun getStores(userId: Int): List<StoreDTO>
    fun saveStore(storeDTO: StoreDTO)
    fun setOpen(id: Int, isOpen: Boolean)
}
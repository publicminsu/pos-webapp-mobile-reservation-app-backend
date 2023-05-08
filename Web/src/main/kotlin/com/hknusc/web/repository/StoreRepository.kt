package com.hknusc.web.repository
import com.hknusc.web.dto.StoreDTO
import org.apache.ibatis.annotations.Mapper
@Mapper
interface StoreRepository {
    fun getStores():List<StoreDTO>
    fun saveStore(storeDTO: StoreDTO)
}
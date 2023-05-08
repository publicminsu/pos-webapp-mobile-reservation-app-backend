package com.hknusc.web.service
import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.repository.StoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StoreService {
    @Autowired
    lateinit var storeRepository:StoreRepository
    fun getStores()=storeRepository.getStores()
    fun saveStore(storeDTO: StoreDTO)=storeRepository.saveStore(storeDTO)
}
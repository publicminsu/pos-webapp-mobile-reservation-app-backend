package com.hknusc.web.service

import com.hknusc.web.dto.store.StoreDTO
import com.hknusc.web.repository.StoreRepository
import com.hknusc.web.util.PhotoUtility
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val photoUtility: PhotoUtility,
    private val storeRepository: StoreRepository
) {
    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double) =
        storeRepository.getStoresByCoordinate(latitude, longitude, distance)

    fun getStore(storeId: Int): StoreDTO {
        val storeDB = storeRepository.getStore(storeId)

        return storeDB.convertToStore(photoUtility)
    }
}

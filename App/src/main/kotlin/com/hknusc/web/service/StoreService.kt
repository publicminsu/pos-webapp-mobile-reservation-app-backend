package com.hknusc.web.service

import com.hknusc.web.dto.store.DistanceDTO
import com.hknusc.web.dto.store.DistanceStoreDBDTO
import com.hknusc.web.dto.store.DistanceStoreDTO
import com.hknusc.web.dto.store.StoreDTO
import com.hknusc.web.repository.OperatingDayRepository
import com.hknusc.web.repository.StoreRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val photoUtility: PhotoUtility,
    private val storeRepository: StoreRepository,
    private val operatingDayRepository: OperatingDayRepository
) {
    fun getStoresByCoordinate(distanceDTO: DistanceDTO): List<DistanceStoreDTO> {
        val distanceStoreDBs = storeRepository.getStoresByCoordinate(distanceDTO)

        val distanceStores: MutableList<DistanceStoreDTO> = mutableListOf()

        distanceStoreDBs.forEach {
            val operatingDays = operatingDayRepository.getDays(it.id)
            val distanceStore = it.convertToStore(photoUtility, operatingDays)
            distanceStores.add(distanceStore)
        }

        return distanceStores
    }

    fun getStore(storeId: Int): StoreDTO {
        val storeDB = try {
            storeRepository.getStore(storeId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.STORE_NOT_FOUND)
        }

        val operatingDays = operatingDayRepository.getDays(storeId)

        return storeDB.convertToStore(photoUtility, operatingDays)
    }
}

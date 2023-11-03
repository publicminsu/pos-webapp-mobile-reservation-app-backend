package com.hknusc.web.service

import com.hknusc.web.dto.store.DistanceDTO
import com.hknusc.web.dto.store.DistanceStoreDTO
import com.hknusc.web.repository.OperatingDayRepository
import com.hknusc.web.repository.TableOrderRepository
import com.hknusc.web.util.PhotoUtility
import org.springframework.stereotype.Service

@Service
class TableOrderService(
    private val photoUtility: PhotoUtility,
    private val tableOrderRepository: TableOrderRepository,
    private val operatingDayRepository: OperatingDayRepository
) {
    fun getStoresByCoordinate(distanceDTO: DistanceDTO): List<DistanceStoreDTO> {
        val distanceStoreDBs = tableOrderRepository.getStoresByCoordinate(distanceDTO)

        val distanceStores: MutableList<DistanceStoreDTO> = mutableListOf()

        distanceStoreDBs.forEach {
            val operatingDays = operatingDayRepository.getDays(it.id)
            val distanceStore = it.convertToStore(photoUtility, operatingDays)
            distanceStores.add(distanceStore)
        }

        return distanceStores
    }

    fun getMenusByTableId(tableId: Int) = tableOrderRepository.getMenusByTableId(tableId)
}

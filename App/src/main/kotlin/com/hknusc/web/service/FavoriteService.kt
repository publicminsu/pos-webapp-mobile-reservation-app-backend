package com.hknusc.web.service

import com.hknusc.web.dto.favorite.FavoriteDTO
import com.hknusc.web.dto.store.StoreDTO
import com.hknusc.web.repository.FavoriteRepository
import com.hknusc.web.repository.OperatingDayRepository
import com.hknusc.web.util.PhotoUtility
import org.springframework.stereotype.Service

@Service
class FavoriteService(
    private val photoUtility: PhotoUtility,
    private val favoriteRepository: FavoriteRepository,
    private val operatingDayRepository: OperatingDayRepository
) {
    fun getFavoriteStores(userId: Int): List<StoreDTO> {
        val storeDBs = favoriteRepository.getFavoriteStores(userId)

        val stores: MutableList<StoreDTO> = mutableListOf()

        storeDBs.forEach {
            val operatingDays = operatingDayRepository.getDays(it.id)
            val store = it.convertToStore(photoUtility, operatingDays)
            stores.add(store)
        }

        return stores
    }

    fun saveFavorite(userId: Int, storeId: Int) =
        favoriteRepository.saveFavorite(FavoriteDTO(accountId = userId, storeId = storeId))

    fun deleteFavorite(userId: Int, storeId: Int) =
        favoriteRepository.deleteFavorite(FavoriteDTO(accountId = userId, storeId = storeId))
}

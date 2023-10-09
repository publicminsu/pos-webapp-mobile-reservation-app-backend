package com.hknusc.web.repository

import com.hknusc.web.dto.favorite.FavoriteDTO
import com.hknusc.web.dto.store.StoreDBDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface FavoriteRepository {
    fun getFavoriteStores(userId: Int): List<StoreDBDTO>
    fun saveFavorite(favoriteDTO: FavoriteDTO)
    fun deleteFavorite(favoriteDTO: FavoriteDTO)
}

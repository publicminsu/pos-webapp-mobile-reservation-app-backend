package com.hknusc.web.controller

import com.hknusc.web.service.FavoriteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores/favorites")
class FavoriteController(private val favoriteService: FavoriteService) {
    @GetMapping
    fun getFavoriteStores(@RequestAttribute userId: Int) = favoriteService.getFavoriteStores(userId)

    @PostMapping
    fun saveFavorite(@RequestAttribute userId: Int, storeId: Int) = favoriteService.saveFavorite(userId, storeId)

    @DeleteMapping
    fun deleteFavorite(@RequestAttribute userId: Int, storeId: Int) = favoriteService.deleteFavorite(userId, storeId)
}

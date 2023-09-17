package com.hknusc.web.controller

import com.hknusc.web.service.StoreService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores")
class StoreController(private val storeService: StoreService) {
    //좌표 기준으로 가게 찾기
    @GetMapping
    fun getStoresByCoordinate(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam distance: Double
    ) =
        storeService.getStoresByCoordinate(latitude, longitude, distance)

    //특정 가게 정보 가져오기
    @GetMapping("{storeId}")
    fun getStore(@PathVariable storeId: Int) =
        storeService.getStore(storeId)
}
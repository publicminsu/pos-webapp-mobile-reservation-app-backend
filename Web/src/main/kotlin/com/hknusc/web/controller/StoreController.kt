package com.hknusc.web.controller

import com.hknusc.web.dto.store.StoreOpenDTO
import com.hknusc.web.dto.store.StoreSaveDTO
import com.hknusc.web.service.StoreService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores")
class StoreController(private val storeService: StoreService) {
    //현재 인증된 사용자의 가게 가져오기
    @GetMapping
    fun getStores(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = storeService.getStores(accessToken)

    @GetMapping("coordinate")
    fun getStoresByCoordinate(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam distance: Double
    ) =
        storeService.getStoresByCoordinate(latitude, longitude, distance)

    //가게 저장하기
    @PostMapping
    fun saveStore(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, storeSaveDTO: StoreSaveDTO) =
        storeService.saveStore(accessToken, storeSaveDTO)

    //가게 개점
    @PostMapping("open")
    fun setOpen(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, storeOpenDTO: StoreOpenDTO) =
        storeService.setOpen(accessToken, storeOpenDTO)
}

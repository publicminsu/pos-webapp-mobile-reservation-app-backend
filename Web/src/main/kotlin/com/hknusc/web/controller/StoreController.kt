package com.hknusc.web.controller

import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.dto.StoreOpenDTO
import com.hknusc.web.util.jwt.JwtTokenProvider
import com.hknusc.web.service.StoreService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores")
class StoreController(private val storeService: StoreService) {
    //현재 인증된 사용자의 가게 가져오기
    @GetMapping
    fun getStores(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = storeService.getStores(accessToken)

    //가게 저장하기
    @PostMapping
    fun saveStore(storeDTO: StoreDTO) = storeService.saveStore(storeDTO)

    //가게 개점
    @PostMapping("open")
    fun setOpen(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String, storeOpenDTO: StoreOpenDTO) =
        storeService.setOpen(accessToken, storeOpenDTO)
}
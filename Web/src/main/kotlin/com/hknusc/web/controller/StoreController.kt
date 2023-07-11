package com.hknusc.web.controller

import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.service.StoreService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores")
class StoreController(private val storeService: StoreService) {
    @GetMapping
    fun getStores(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) = storeService.getStores(accessToken)

    @PostMapping
    fun saveStore(storeDTO: StoreDTO) = storeService.saveStore(storeDTO)

    @PostMapping("open")//나누어야 하는가?
    fun setOpen(id: Int, isOpen: Boolean) = storeService.setOpen(id, isOpen)
}
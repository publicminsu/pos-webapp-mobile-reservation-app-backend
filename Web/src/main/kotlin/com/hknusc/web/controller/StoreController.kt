package com.hknusc.web.controller

import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("stores")
class StoreController {
    @Autowired
    lateinit var storeService: StoreService
    @GetMapping
    fun getStores()=storeService.getStores()
    @PostMapping
    fun saveStore(storeDTO: StoreDTO)=storeService.saveStore(storeDTO)
}
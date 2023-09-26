package com.hknusc.web.controller

import com.hknusc.web.dto.store.StoreEditDTO
import com.hknusc.web.dto.store.StoreOpenDTO
import com.hknusc.web.dto.store.StoreSaveDTO
import com.hknusc.web.service.StoreService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stores")
class StoreController(private val storeService: StoreService) {
    //현재 인증된 사용자의 가게 가져오기
    @GetMapping
    fun getStores(@RequestAttribute userId: Int) = storeService.getStores(userId)

    //현재 토큰으로 개점한 가게 가져오기
    @GetMapping("{storeId}")
    fun getStore(@RequestAttribute userId: Int, @RequestAttribute userStoreId: Int, @PathVariable storeId: Int) =
        storeService.getStore(userId, userStoreId)

    //가게 저장하기
    @PostMapping
    fun saveStore(@RequestAttribute userId: Int, @Valid storeSaveDTO: StoreSaveDTO) =
        storeService.saveStore(userId, storeSaveDTO)

    //가게 수정하기
    @PatchMapping
    fun editStore(
        @RequestAttribute userId: Int,
        @RequestAttribute userStoreId: Int,
        @Valid storeEditDTO: StoreEditDTO
    ) =
        storeService.editStore(userId, userStoreId, storeEditDTO)

    //가게 개점
    @PostMapping("open")
    fun setOpen(@RequestAttribute userId: Int, @RequestAttribute userEmail: String, storeOpenDTO: StoreOpenDTO) =
        storeService.setOpen(userId, userEmail, storeOpenDTO)
}

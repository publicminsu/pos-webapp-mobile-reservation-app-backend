package com.hknusc.web.service

import com.hknusc.web.dto.store.*
import com.hknusc.web.repository.StoreRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val tokenProvider: JWTTokenProvider,
    private val photoUtility: PhotoUtility,
    private val storeRepository: StoreRepository
) {
    fun getStores(userId: Int): List<StoreDTO> {
        val storesDB = storeRepository.getStores(userId)

        val storeList: MutableList<StoreDTO> = mutableListOf()
        storesDB.forEach {
            val store = it.convertToStore(photoUtility)
            storeList.add(store)
        }

        return storeList
    }

    fun getStore(userId: Int, userStoreId: Int): StoreDTO {
        val storeDB = storeRepository.getStore(userId, userStoreId)

        return storeDB.convertToStore(photoUtility)
    }

    fun saveStore(userId: Int, storeSaveDTO: StoreSaveDTO) {
        val storeDBSaveDTO = storeSaveDTO.convertToStoreDB(photoUtility, userId)

        storeRepository.saveStore(storeDBSaveDTO)
    }

    fun editStore(userId: Int, userStoreId: Int, storeEditDTO: StoreEditDTO) {
        val storeDB = storeRepository.getStore(userId, userStoreId)
        storeDB.profilePhoto?.let { photoUtility.deleteImage(it) }
        photoUtility.deleteImages(storeDB.photos)

        val storeDBEditDTO = storeEditDTO.convertToStoreDB(photoUtility, userStoreId)

        storeRepository.editStore(storeDBEditDTO)
    }

    fun setOpen(userId: Int, userEmail: String, storeOpenDTO: StoreOpenDTO): ResponseEntity<Any> {
        if (storeRepository.setOpen(userId, storeOpenDTO) == 0)
            throw CustomException(ErrorCode.STORE_NOT_FOUND)

        val responseEntity = ResponseEntity.ok()
        if (storeOpenDTO.isOpen) {
            val httpHeaders: HttpHeaders = tokenProvider.generateTokenHeader(userId, userEmail, storeOpenDTO.id)
            responseEntity.headers(httpHeaders)
        }
        return responseEntity.build()
    }
}

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
    fun getStores(bearerAccessToken: String): List<StoreDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val storesDB = storeRepository.getStores(userId)

        val storeList: MutableList<StoreDTO> = mutableListOf()
        storesDB.forEach {
            val store = it.convertToStore(photoUtility)
            storeList.add(store)
        }

        return storeList
    }

    fun getStore(bearerAccessToken: String): StoreDTO {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)
        val storeId = tokenProvider.findUserStoreIdByClaims(claims)

        val storeDB = storeRepository.getStore(userId, storeId)

        return storeDB.convertToStore(photoUtility)
    }

    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double) =
        storeRepository.getStoresByCoordinate(latitude, longitude, distance)

    fun saveStore(bearerAccessToken: String, storeSaveDTO: StoreSaveDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val storeDBSaveDTO = storeSaveDTO.convertToStoreDB(photoUtility, userId)

        storeRepository.saveStore(storeDBSaveDTO)
    }

    fun editStore(bearerAccessToken: String, storeEditDTO: StoreEditDTO) {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)
        val storeId = tokenProvider.findUserStoreIdByClaims(claims)

        val storeDB = storeRepository.getStore(userId, storeId)
        storeDB.profilePhoto?.let { photoUtility.deleteImage(it) }
        photoUtility.deleteImages(storeDB.photos)

        val storeDBEditDTO = storeEditDTO.convertToStoreDB(photoUtility, storeId)

        storeRepository.editStore(storeDBEditDTO)
    }

    fun setOpen(bearerAccessToken: String, storeOpenDTO: StoreOpenDTO): ResponseEntity<Any> {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)
        val userEmail = tokenProvider.findUserEmailByClaims(claims)

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

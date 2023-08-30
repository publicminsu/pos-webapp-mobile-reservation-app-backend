package com.hknusc.web.service

import com.hknusc.web.dto.store.*
import com.hknusc.web.repository.StoreRepository
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import io.jsonwebtoken.Claims
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val tokenProvider: JwtTokenProvider, private val storeRepository: StoreRepository
) {
    fun getStores(bearerAccessToken: String): List<StoreDTO> {
        val userId = getUserId(bearerAccessToken)

        return storeRepository.getStores(userId)
    }

    fun getStoresByCoordinate(latitude: Double, longitude: Double, distance: Double) =
        storeRepository.getStoresByCoordinate(latitude, longitude, distance)

    fun saveStore(bearerAccessToken: String, storeSaveDTO: StoreSaveDTO) {
        val userId = getUserId(bearerAccessToken)

        val storeDBSaveDTO = StoreDBSaveDTO(
            userId,
            storeSaveDTO.name,
            storeSaveDTO.latitude,
            storeSaveDTO.longitude,
            storeSaveDTO.address,
            storeSaveDTO.info,
            storeSaveDTO.phoneNumber,
            storeSaveDTO.canReservation,
            storeSaveDTO.operatingTime
        )

        storeRepository.saveStore(storeDBSaveDTO)
    }

    fun editStore(bearerAccessToken: String, storeEditDTO: StoreEditDTO) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        val storeDBEditDTO = StoreDBEditDTO(
            userStoreId,
            storeEditDTO.name,
            storeEditDTO.latitude,
            storeEditDTO.longitude,
            storeEditDTO.address,
            storeEditDTO.info,
            storeEditDTO.phoneNumber,
            storeEditDTO.canReservation,
            storeEditDTO.operatingTime
        )

        storeRepository.editStore(storeDBEditDTO)
    }

    fun setOpen(bearerAccessToken: String, storeOpenDTO: StoreOpenDTO): ResponseEntity<Any> {
        val claims = getClaims(bearerAccessToken)
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

    private fun getClaims(bearerAccessToken: String): Claims {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)
        return tokenProvider.findClaimsByJWT(accessToken)
    }

    private fun getUserId(bearerAccessToken: String): Int {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)
        return tokenProvider.findUserIdByJWT(accessToken)
    }
}

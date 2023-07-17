package com.hknusc.web.service

import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.dto.StoreOpenDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import com.hknusc.web.repository.StoreRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val tokenProvider: JwtTokenProvider, private val storeRepository: StoreRepository
) {
    fun getStores(bearerAccessToken: String): List<StoreDTO> {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val userId = tokenProvider.findUserIdByJWT(accessToken)

        return storeRepository.getStores(userId)
    }

    fun saveStore(storeDTO: StoreDTO) = storeRepository.saveStore(storeDTO)
    fun setOpen(bearerAccessToken: String, storeOpenDTO: StoreOpenDTO): ResponseEntity<Any> {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        val claims = tokenProvider.findClaimsByJWT(accessToken)
        val userId = tokenProvider.findUserIdByClaims(claims).toInt()
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
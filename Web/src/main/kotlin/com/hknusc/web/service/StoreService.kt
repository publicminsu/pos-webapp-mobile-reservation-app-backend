package com.hknusc.web.service

import com.hknusc.web.dto.StoreDTO
import com.hknusc.web.jwt.JwtTokenProvider
import com.hknusc.web.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val tokenProvider: JwtTokenProvider, private val storeRepository: StoreRepository
) {
    fun getStores(bearerAccessToken: String): List<StoreDTO> {
        val accessToken = tokenProvider.resolveToken(bearerAccessToken)

        tokenProvider.validateToken(accessToken.toString())

        val userId = tokenProvider.findUserIdByJWT(accessToken)

        return storeRepository.getStores(userId)
    }

    fun saveStore(storeDTO: StoreDTO) = storeRepository.saveStore(storeDTO)
    fun setOpen(id: Int, isOpen: Boolean) = storeRepository.setOpen(id, isOpen)
}
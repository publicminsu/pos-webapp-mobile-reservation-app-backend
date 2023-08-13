package com.hknusc.web.repository

import com.hknusc.web.dto.auth.RefreshTokenDTO
import com.hknusc.web.dto.auth.RefreshTokenSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AuthRepository {
    fun getRefreshToken(accountId: Int): RefreshTokenDTO
    fun saveRefreshToken(refreshTokenSaveDTO: RefreshTokenSaveDTO)
    fun removeRefreshToken(refreshTokenId: Int)
}
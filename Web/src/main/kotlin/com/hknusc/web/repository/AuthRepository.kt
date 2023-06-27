package com.hknusc.web.repository

import com.hknusc.web.dto.RefreshTokenDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AuthRepository {
    fun getRefreshToken(accountId: Int): RefreshTokenDTO
    fun saveRefreshToken(refreshTokenDTO: RefreshTokenDTO)
}
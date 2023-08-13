package com.hknusc.web.dto.auth

data class RefreshTokenDTO(
    var id: Int = 0,
    var accountId: Int,
    var refreshToken: String?
)

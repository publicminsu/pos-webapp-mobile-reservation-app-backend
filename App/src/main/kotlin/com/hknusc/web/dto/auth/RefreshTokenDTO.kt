package com.hknusc.web.dto.auth

data class RefreshTokenDTO(
    var id: Int,
    var accountId: Int,
    var refreshToken: String
)

package com.hknusc.web.dto.auth

data class RefreshTokenSaveDTO(
    var accountId: Int,
    var refreshToken: String
)

package com.hknusc.web.dto

data class RefreshTokenDTO(
    var id: Int = 0,
    var accountId: Int,
    var refreshToken: String?
)

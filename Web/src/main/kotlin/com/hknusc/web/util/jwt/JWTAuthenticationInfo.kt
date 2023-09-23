package com.hknusc.web.util.jwt

data class JWTAuthenticationInfo(
    val id: Int,
    val email: String,
    val storeId: Int,
)

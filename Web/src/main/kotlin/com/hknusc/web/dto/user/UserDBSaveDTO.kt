package com.hknusc.web.dto.user

data class UserDBSaveDTO(
    val email: String,
    val password: String,
    val nickname: String,
    val phoneNumber: String,
    var id: Int = 0,
    val isVerified: Boolean = false
)

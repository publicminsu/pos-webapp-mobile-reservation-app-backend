package com.hknusc.web.dto.user

data class UserSaveDTO(
    val email: String,
    val password: String,
    val nickname: String,
    val phoneNumber: String,
)

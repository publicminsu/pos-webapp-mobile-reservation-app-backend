package com.hknusc.web.dto.user

data class UserDBSaveDTO(
    var id: Int = 0,
    val email: String,
    val password: String,
    val nickname: String,
    val phoneNumber: String,
    val isVerified: Boolean = false,
    val profilePhoto: String?
)

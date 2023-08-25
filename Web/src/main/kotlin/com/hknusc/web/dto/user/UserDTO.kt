package com.hknusc.web.dto.user

data class UserDTO(
    var id: Int,
    var email: String,
    var password: String,
    var nickname: String,
    var phoneNumber: String,
    var paymentCard: String?,
    var isVerified: Boolean
)

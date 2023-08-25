package com.hknusc.web.dto.user

data class UserEditDTO(
    var email: String,
    var nickname: String,
    var phoneNumber: String,
    var paymentCard: String?,
)

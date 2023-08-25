package com.hknusc.web.dto.user

data class UserDBEditDTO(
    var id: Int,
    var email: String,
    var nickname: String,
    var phoneNumber: String,
    var paymentCard: String?,
)

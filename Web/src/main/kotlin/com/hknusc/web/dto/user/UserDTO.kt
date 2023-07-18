package com.hknusc.web.dto.user

data class UserDTO(
    var id: Int = 0,
    var email: String,
    var password: String,
    var nickname: String,
    var phoneNumber: String,
    var wishList: String?,
    var couponList: String?,
    var paymentCard: String?,
)

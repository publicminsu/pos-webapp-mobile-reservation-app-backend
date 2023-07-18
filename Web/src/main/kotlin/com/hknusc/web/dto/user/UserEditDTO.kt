package com.hknusc.web.dto.user

data class UserEditDTO(
    var nickname: String,
    var phoneNumber: String,
    var wishList: String?,
    var couponList: String?,
    var paymentCard: String?,
)

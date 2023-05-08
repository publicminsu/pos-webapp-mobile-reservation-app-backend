package com.hknusc.web.dto
data class UserDTO(
    var id:Int=0,
    var email:String,
    var password:String,
    var nickname:String,
    var phone_number:String,
    var wish_list:String?,
    var coupon_list:String?,
    var payment_card:String?,
)

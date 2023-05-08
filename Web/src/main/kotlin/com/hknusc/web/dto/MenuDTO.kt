package com.hknusc.web.dto
data class MenuDTO(
    var id:Int=0,
    var storeId:Int,
    var photo:String?,
    var name: String,
    var price: String,
    var category:String?
)

package com.hknusc.web.dto

data class MenuEditDTO(
    var id: Int = 0,
    var photo: String?,
    var name: String,
    var price: String,
    var category: String?
)

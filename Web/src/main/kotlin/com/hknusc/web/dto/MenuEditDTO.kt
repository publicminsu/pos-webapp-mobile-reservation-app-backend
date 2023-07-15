package com.hknusc.web.dto

data class MenuEditDTO(
    var id: Int,
    var photo: String?,
    var name: String,
    var price: String,
    var category: String?
)

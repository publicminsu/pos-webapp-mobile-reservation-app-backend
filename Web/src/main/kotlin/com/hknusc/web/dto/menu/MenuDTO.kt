package com.hknusc.web.dto.menu

data class MenuDTO(
    var id: Int = 0,
    var storeId: Int,
    var photo: String?,
    var name: String,
    var price: String,
    var category: String?
)

package com.hknusc.web.dto.menu

data class MenuDBSaveDTO(
    val storeId: Int,
    val name: String,
    val price: Int,
    val photo: String?,
    val category: String?
)

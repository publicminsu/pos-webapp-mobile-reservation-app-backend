package com.hknusc.web.dto.menu

data class MenuDTO(
    val id: Int,
    val storeId: Int,
    val name: String,
    val price: Int,
    val photo: String?,
    val category: String?,
    val detail: String?
)

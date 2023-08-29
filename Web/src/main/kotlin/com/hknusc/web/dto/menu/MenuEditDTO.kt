package com.hknusc.web.dto.menu

import org.springframework.web.multipart.MultipartFile

data class MenuEditDTO(
    val id: Int,
    val name: String,
    val price: Int,
    val photo: MultipartFile?,
    val category: String?
)

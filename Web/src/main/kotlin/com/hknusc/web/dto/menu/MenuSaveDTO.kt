package com.hknusc.web.dto.menu

import org.springframework.web.multipart.MultipartFile

data class MenuSaveDTO(
    val name: String,
    val price: Int,
    val photo: MultipartFile?,
    val category: String?
)

package com.hknusc.web.dto.menu

import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile

data class MenuSaveDTO(
    val name: String,
    @PositiveOrZero
    val price: Int,
    val photo: MultipartFile?,
    val category: String?
)

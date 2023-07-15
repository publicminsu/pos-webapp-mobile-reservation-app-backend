package com.hknusc.web.dto

import org.springframework.web.multipart.MultipartFile

data class MenuSaveDTO(
    var photo: MultipartFile?,
    var name: String,
    var price: String,
    var category: String?
)

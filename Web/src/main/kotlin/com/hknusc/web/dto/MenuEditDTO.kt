package com.hknusc.web.dto

import org.springframework.web.multipart.MultipartFile

data class MenuEditDTO(
    var id: Int,
    var photo: MultipartFile?,
    var name: String,
    var price: String,
    var category: String?
)

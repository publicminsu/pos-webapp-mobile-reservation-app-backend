package com.hknusc.web.dto.review

import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class ReviewDBEditDTO(
    val id: Int,
    val accountId: Int,
    val detail: String?,
    val writingTime: Timestamp,
    val rating: Int,
    val photos: String
)

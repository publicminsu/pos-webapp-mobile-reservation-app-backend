package com.hknusc.web.dto.review

import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class ReviewDBSaveDTO(
    val accountId: Int,
    val storeId: Int,
    val detail: String?,
    val writingTime: Timestamp,
    val rating: Int,
    val photos: String
)

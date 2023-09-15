package com.hknusc.web.dto.review

import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class ReviewSaveDTO(
    val detail: String?,
    val writingTime: Timestamp,
    @PositiveOrZero
    val rating: Int,
    val photos: List<MultipartFile>?
)

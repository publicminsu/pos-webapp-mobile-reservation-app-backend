package com.hknusc.web.dto.review

import java.sql.Timestamp

data class ReviewDBDTO(
    val id: Int,
    val accountId: Int,
    val storeId: Int,
    val detail: String?,
    val writingTime: Timestamp,
    val rating: Int,
    val photos: String
)

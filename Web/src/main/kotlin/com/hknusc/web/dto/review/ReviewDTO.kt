package com.hknusc.web.dto.review

import java.sql.Timestamp

data class ReviewDTO(
    var id: Int,
    var accountId: Int,
    var storeId: Int,
    var detail: String?,
    var writingTime: Timestamp,
    var rating: Int,
)

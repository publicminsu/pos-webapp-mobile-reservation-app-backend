package com.hknusc.web.dto

import java.sql.Timestamp

data class ReviewDTO(
    var id: Int = 0,
    var accountId: Int,
    var storeId: Int,
    var detail: String?,
    var writingTime: Timestamp,
    var rating: Int,
)

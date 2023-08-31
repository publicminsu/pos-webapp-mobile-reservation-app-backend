package com.hknusc.web.dto.review

import com.hknusc.web.util.PhotoUtility
import java.sql.Timestamp

data class ReviewDBDTO(
    val id: Int,
    val accountId: Int,
    val storeId: Int,
    val detail: String?,
    val writingTime: Timestamp,
    val rating: Int,
    val photos: String
) {
    fun convertToReview(photoUtility: PhotoUtility): ReviewDTO = ReviewDTO(
        id,
        accountId,
        storeId,
        detail,
        writingTime,
        rating,
        photoUtility.getImagesByString(photos)
    )
}

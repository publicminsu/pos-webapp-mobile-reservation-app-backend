package com.hknusc.web.dto.review

import com.hknusc.web.util.PhotoUtility
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class ReviewSaveDTO(
    val storeId: Int,
    val detail: String?,
    val writingTime: Timestamp,
    @PositiveOrZero
    val rating: Int,
    val photos: List<MultipartFile>?
) {
    fun convertToReviewDB(photoUtility: PhotoUtility, accountId: Int) = ReviewDBSaveDTO(
        accountId = accountId,
        storeId = storeId,
        detail = detail,
        writingTime = writingTime,
        rating = rating,
        photos = photoUtility.saveImagesAsString(photos)
    )
}

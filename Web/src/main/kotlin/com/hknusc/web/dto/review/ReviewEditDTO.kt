package com.hknusc.web.dto.review

import com.hknusc.web.util.PhotoUtility
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class ReviewEditDTO(
    val id: Int,
    val detail: String?,
    val writingTime: Timestamp,
    @field:PositiveOrZero val rating: Int,
    val photos: List<MultipartFile>?
) {
    fun convertToReviewDB(photoUtility: PhotoUtility, accountId: Int): ReviewDBEditDTO =
        ReviewDBEditDTO(id, accountId, detail, writingTime, rating, photoUtility.saveImagesAsString(photos))
}

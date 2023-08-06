package com.hknusc.web.repository

import com.hknusc.web.dto.review.ReviewDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReviewRepository {
    fun getReviews(storeId: Int): List<ReviewDTO>
    fun getReview(reviewId: Int): ReviewDTO?
}
package com.hknusc.web.repository

import com.hknusc.web.dto.review.ReviewDBSaveDTO
import com.hknusc.web.dto.review.ReviewDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReviewRepository {
    fun getReviews(storeId: Int): List<ReviewDTO>
    fun getReview(storeId: Int, reviewId: Int): ReviewDTO?
    fun saveReview(reviewDBSaveDTO: ReviewDBSaveDTO)
}

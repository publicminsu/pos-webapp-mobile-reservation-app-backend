package com.hknusc.web.repository

import com.hknusc.web.dto.review.ReviewDBDTO
import com.hknusc.web.dto.review.ReviewDBSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReviewRepository {
    fun getReviews(storeId: Int): List<ReviewDBDTO>
    fun getReview(storeId: Int, reviewId: Int): ReviewDBDTO?
    fun saveReview(reviewDBSaveDTO: ReviewDBSaveDTO)
    fun deleteReview(userId: Int, reviewId: Int): Int
}

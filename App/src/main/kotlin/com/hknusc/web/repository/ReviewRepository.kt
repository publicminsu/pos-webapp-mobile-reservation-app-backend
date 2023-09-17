package com.hknusc.web.repository

import com.hknusc.web.dto.review.ReviewDBDTO
import com.hknusc.web.dto.review.ReviewDBEditDTO
import com.hknusc.web.dto.review.ReviewDBSaveDTO
import com.hknusc.web.dto.review.ReviewDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReviewRepository {
    fun getReviews(userId: Int): List<ReviewDBDTO>
    fun getReview(userId: Int, reviewId: Int): ReviewDBDTO?
    fun getReviewsByStore(userId: Int, storeId: Int): List<ReviewDBDTO>
    fun saveReview(reviewDBSaveDTO: ReviewDBSaveDTO)
    fun editReview(reviewDBEditDTO: ReviewDBEditDTO): Int
    fun deleteReview(userId: Int, reviewId: Int): Int
}

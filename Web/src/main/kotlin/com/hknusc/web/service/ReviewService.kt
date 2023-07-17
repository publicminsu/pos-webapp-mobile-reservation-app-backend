package com.hknusc.web.service

import com.hknusc.web.dto.ReviewDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.repository.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(private val reviewRepository: ReviewRepository) {
    fun getReviews(storeId: Int) = reviewRepository.getReviews(storeId)
    fun getReview(reviewId: Int): ReviewDTO {
        try {
            return reviewRepository.getReview(reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
    }
}
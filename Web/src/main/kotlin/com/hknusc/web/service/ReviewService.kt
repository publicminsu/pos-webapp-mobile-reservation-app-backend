package com.hknusc.web.service

import com.hknusc.web.dto.review.ReviewDTO
import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.repository.ReviewRepository
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val tokenProvider: JwtTokenProvider, private val reviewRepository: ReviewRepository
) {
    fun getReviews(bearerAccessToken: String): List<ReviewDTO> {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        return reviewRepository.getReviews(userStoreId)
    }

    fun getReview(bearerAccessToken: String, reviewId: Int): ReviewDTO {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        try {
            return reviewRepository.getReview(userStoreId, reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
    }

    fun saveReview(bearerAccessToken: String, reviewSaveDTO: ReviewSaveDTO) {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

    }
}

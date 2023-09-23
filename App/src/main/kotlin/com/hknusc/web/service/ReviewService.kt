package com.hknusc.web.service

import com.hknusc.web.dto.review.ReviewDTO
import com.hknusc.web.dto.review.ReviewEditDTO
import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.repository.ReviewRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JWTTokenProvider
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val tokenProvider: JWTTokenProvider,
    private val photoUtility: PhotoUtility,
    private val reviewRepository: ReviewRepository
) {
    fun getReviews(bearerAccessToken: String): List<ReviewDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val dbReviews = reviewRepository.getReviews(userId)

        val reviews: MutableList<ReviewDTO> = mutableListOf()
        dbReviews.forEach {
            reviews.add(it.convertToReview(photoUtility))
        }
        return reviews
    }

    fun getReviewsByStore(bearerAccessToken: String, storeId: Int): List<ReviewDTO> {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val dbReviews = reviewRepository.getReviewsByStore(userId, storeId)

        val reviews: MutableList<ReviewDTO> = mutableListOf()
        dbReviews.forEach {
            reviews.add(it.convertToReview(photoUtility))
        }
        return reviews
    }

    fun saveReview(bearerAccessToken: String, reviewSaveDTO: ReviewSaveDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        val reviewDBSaveDTO = reviewSaveDTO.convertToReviewDB(photoUtility, userId)

        reviewRepository.saveReview(reviewDBSaveDTO)
    }

    fun editReview(bearerAccessToken: String, reviewEditDTO: ReviewEditDTO) {
        val userId = tokenProvider.findUserIdByBearerAccessToken(bearerAccessToken)

        //이전 이미지 삭제
        val oldDBReview = try {
            reviewRepository.getReview(userId, reviewEditDTO.id)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
        photoUtility.deleteImages(oldDBReview.photos)

        val reviewDBEditDTO = reviewEditDTO.convertToReviewDB(photoUtility, userId)

        if (reviewRepository.editReview(reviewDBEditDTO) == 0) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
    }

    fun deleteReview(bearerAccessToken: String, reviewId: Int) {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)

        //이전 이미지 삭제
        val oldDBReview = try {
            reviewRepository.getReview(userId, reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        if (reviewRepository.deleteReview(userId, reviewId) == 0) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
        photoUtility.deleteImages(oldDBReview.photos)
    }
}

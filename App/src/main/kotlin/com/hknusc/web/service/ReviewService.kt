package com.hknusc.web.service

import com.hknusc.web.dto.review.ReviewDBSaveDTO
import com.hknusc.web.dto.review.ReviewDTO
import com.hknusc.web.dto.review.ReviewEditDTO
import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.repository.ReviewRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val tokenProvider: JwtTokenProvider,
    private val photoUtility: PhotoUtility,
    private val reviewRepository: ReviewRepository
) {
    fun getReviews(bearerAccessToken: String): List<ReviewDTO> {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        val dbReviews = reviewRepository.getReviews(userStoreId)

        val reviews: MutableList<ReviewDTO> = mutableListOf()
        dbReviews.forEach {
            reviews.add(it.convertToReview(photoUtility))
        }
        return reviews
    }

    fun getReview(bearerAccessToken: String, reviewId: Int): ReviewDTO {
        val userStoreId = tokenProvider.findUserStoreIdByBearerAccessToken(bearerAccessToken)

        val dbReview = try {
            reviewRepository.getReview(userStoreId, reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        return dbReview.convertToReview(photoUtility)
    }

    fun saveReview(bearerAccessToken: String, reviewSaveDTO: ReviewSaveDTO) {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims)

        val photos: String = photoUtility.saveImagesAsString(reviewSaveDTO.photos)
        val reviewDBSaveDTO =
            ReviewDBSaveDTO(
                userId,
                userStoreId,
                reviewSaveDTO.detail,
                reviewSaveDTO.writingTime,
                reviewSaveDTO.rating,
                photos
            )

        reviewRepository.saveReview(reviewDBSaveDTO)
    }

    fun editReview(bearerAccessToken: String, reviewEditDTO: ReviewEditDTO) {
        val claims = tokenProvider.findClaimsByBearerAccessToken(bearerAccessToken)
        val userId = tokenProvider.findUserIdByClaims(claims)
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims)

        //이전 이미지 삭제
        val oldDBReview = try {
            reviewRepository.getReview(userStoreId, reviewEditDTO.id)!!
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
        val userStoreId = tokenProvider.findUserStoreIdByClaims(claims)

        //이전 이미지 삭제
        val oldDBReview = try {
            reviewRepository.getReview(userStoreId, reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        if (reviewRepository.deleteReview(userId, reviewId) == 0) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }
        photoUtility.deleteImages(oldDBReview.photos)
    }
}

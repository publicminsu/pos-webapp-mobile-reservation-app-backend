package com.hknusc.web.service

import com.hknusc.web.dto.review.ReviewDTO
import com.hknusc.web.repository.ReviewRepository
import com.hknusc.web.util.PhotoUtility
import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val photoUtility: PhotoUtility,
    private val reviewRepository: ReviewRepository
) {
    fun getReviews(userStoreId: Int): List<ReviewDTO> {
        val dbReviews = reviewRepository.getReviews(userStoreId)

        val reviews: MutableList<ReviewDTO> = mutableListOf()
        dbReviews.forEach {
            reviews.add(it.convertToReview(photoUtility))
        }
        return reviews
    }

    fun getReview(userStoreId: Int, reviewId: Int): ReviewDTO {
        val dbReview = try {
            reviewRepository.getReview(userStoreId, reviewId)!!
        } catch (e: Exception) {
            throw CustomException(ErrorCode.REVIEW_NOT_FOUND)
        }

        return dbReview.convertToReview(photoUtility)
    }
}

package com.hknusc.web.service

import com.hknusc.web.dto.ReviewDTO
import com.hknusc.web.repository.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService(private val reviewRepository: ReviewRepository) {
    fun getReviews(storeId: Int) = reviewRepository.getReviews(storeId)
    fun getReview(reviewId: Int) = reviewRepository.getReview(reviewId)
}
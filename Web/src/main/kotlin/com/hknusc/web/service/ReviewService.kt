package com.hknusc.web.service

import com.hknusc.web.repository.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService {
    @Autowired
    lateinit var reviewRepository: ReviewRepository
    fun getStoreReviews(storeId: Int) = reviewRepository.getStoreReviews(storeId)
}
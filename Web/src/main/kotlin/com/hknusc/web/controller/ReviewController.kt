package com.hknusc.web.controller

import com.hknusc.web.dto.review.ReviewEditDTO
import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.service.ReviewService
import com.hknusc.web.util.jwt.JWTTokenProvider
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reviews")
class ReviewController(private val reviewService: ReviewService) {
    //리뷰 가져오기
    @GetMapping
    fun getReviews(@RequestAttribute userStoreId: Int) =
        reviewService.getReviews(userStoreId)

    //특정 리뷰 가져오기
    @GetMapping("{reviewId}")
    fun getReview(
        @RequestAttribute userStoreId: Int,
        @PathVariable("reviewId") reviewId: Int
    ) = reviewService.getReview(userStoreId, reviewId)
}

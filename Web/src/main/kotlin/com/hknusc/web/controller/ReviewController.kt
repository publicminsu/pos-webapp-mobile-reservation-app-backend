package com.hknusc.web.controller

import com.hknusc.web.service.ReviewService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reviews")
class ReviewController(private val reviewService: ReviewService) {
    @GetMapping
    fun getReviews(@RequestHeader(JwtTokenProvider.Access_Key) accessToken: String) =
        reviewService.getReviews(accessToken)

    @GetMapping("{reviewId}")
    fun getReview(
        @RequestHeader(JwtTokenProvider.Access_Key) accessToken: String,
        @PathVariable("reviewId") reviewId: Int
    ) = reviewService.getReview(accessToken, reviewId)
}

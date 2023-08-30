package com.hknusc.web.controller

import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.service.ReviewService
import com.hknusc.web.util.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reviews")
class ReviewController(private val reviewService: ReviewService) {
    //리뷰 가져오기
    @GetMapping
    fun getReviews(@RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String) =
        reviewService.getReviews(accessToken)

    //특정 리뷰 가져오기
    @GetMapping("{reviewId}")
    fun getReview(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable("reviewId") reviewId: Int
    ) = reviewService.getReview(accessToken, reviewId)

    //리뷰 저장하기(테스트용)
    @PostMapping
    fun saveReview(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        reviewSaveDTO: ReviewSaveDTO
    ) = reviewService.saveReview(accessToken, reviewSaveDTO)
}

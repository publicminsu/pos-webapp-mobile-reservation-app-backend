package com.hknusc.web.controller

import com.hknusc.web.dto.review.ReviewEditDTO
import com.hknusc.web.dto.review.ReviewSaveDTO
import com.hknusc.web.service.ReviewService
import com.hknusc.web.util.jwt.JwtTokenProvider
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reviews")
class ReviewController(private val reviewService: ReviewService) {
    //리뷰 가져오기
    @GetMapping
    fun getReviews(@RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String) =
        reviewService.getReviews(accessToken)

    //특정 리뷰 가져오기
    @GetMapping("{storeId}")
    fun getReviewsByStore(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable storeId: Int
    ) = reviewService.getReviewsByStore(accessToken, storeId)

    //리뷰 저장하기(테스트용)
    @PostMapping
    fun saveReview(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @Valid reviewSaveDTO: ReviewSaveDTO
    ) = reviewService.saveReview(accessToken, reviewSaveDTO)

    //리뷰 수정하기(테스트용)
    @PatchMapping
    fun editReview(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @Valid reviewEditDTO: ReviewEditDTO
    ) =
        reviewService.editReview(accessToken, reviewEditDTO)

    //리뷰 삭제하기(테스트용)
    @DeleteMapping("{reviewId}")
    fun deleteReview(
        @RequestHeader(JwtTokenProvider.ACCESS_KEY) accessToken: String,
        @PathVariable("reviewId") reviewId: Int
    ) = reviewService.deleteReview(accessToken, reviewId)
}

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
    fun getReviews(@RequestAttribute userId: Int) =
        reviewService.getReviews(userId)

    //특정 리뷰 가져오기
    @GetMapping("{storeId}")
    fun getReviewsByStore(
        @RequestAttribute userId: Int,
        @PathVariable storeId: Int
    ) = reviewService.getReviewsByStore(userId, storeId)

    //리뷰 저장하기(테스트용)
    @PostMapping
    fun saveReview(
        @RequestAttribute userId: Int,
        @Valid reviewSaveDTO: ReviewSaveDTO
    ) = reviewService.saveReview(userId, reviewSaveDTO)

    //리뷰 수정하기(테스트용)
    @PatchMapping
    fun editReview(
        @RequestAttribute userId: Int,
        @Valid reviewEditDTO: ReviewEditDTO
    ) =
        reviewService.editReview(userId, reviewEditDTO)

    //리뷰 삭제하기(테스트용)
    @DeleteMapping("{reviewId}")
    fun deleteReview(
        @RequestAttribute userId: Int,
        @PathVariable("reviewId") reviewId: Int
    ) = reviewService.deleteReview(userId, reviewId)
}

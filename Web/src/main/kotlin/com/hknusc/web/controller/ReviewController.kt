package com.hknusc.web.controller

import com.hknusc.web.service.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class ReviewController(private val reviewService: ReviewService) {
    @GetMapping("{storeId}")
    fun getReviews(@PathVariable("storeId") storeId: Int) = reviewService.getReviews(storeId)

    @GetMapping("{storeId}/{reviewId}")
    fun getReview(@PathVariable("reviewId") reviewId: Int) = reviewService.getReview(reviewId)
}
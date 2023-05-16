package com.hknusc.web.controller

import com.hknusc.web.service.ReviewService
import com.hknusc.web.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class ReviewController {
    @Autowired
    lateinit var reviewService: ReviewService

    @GetMapping("{storeId}")
    fun getStoreReviews(@PathVariable("storeId") storeId: Int) = reviewService.getStoreReviews(storeId)
}
package com.hknusc.web.repository

import com.hknusc.web.dto.ReviewDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ReviewRepository {
    fun getStoreReviews(storeId: Int): List<ReviewDTO>
}
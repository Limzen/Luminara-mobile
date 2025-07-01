package com.example.luminara.repository

import com.example.luminara.data.model.Review
import com.example.luminara.data.remote.RetrofitInstance

class ReviewRepository {
    suspend fun getReviewsByDirectoryId(directoryId: Long): List<Review> {
        return RetrofitInstance.reviewApi.getReviewsByDirectoryId(directoryId)
    }

    suspend fun createReview(review: Review): Review {
        return RetrofitInstance.reviewApi.createReview(review)
    }

    suspend fun deleteReview(id: Long): Review {
        return RetrofitInstance.reviewApi.deleteReview(id)
    }
}
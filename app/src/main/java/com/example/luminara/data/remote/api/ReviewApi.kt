package com.example.luminara.data.remote.api

import com.example.luminara.data.model.Review
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {
    @GET("review/directory/{directoryId}")
    suspend fun getReviewsByDirectoryId(@Path("directoryId") id: Long): List<Review>

    @POST("review")
    suspend fun createReview(@Body review: Review): Review

    @DELETE("review/{id}")
    suspend fun deleteReview(@Path("id") id: Long): Review
}
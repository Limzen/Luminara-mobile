package com.example.luminara.data.model

import com.google.gson.annotations.SerializedName

data class Directory(
    val id: Long = 0L,
    val name: String,
    val address: String,
    @SerializedName("overall_rating") val overallRating: Float,
    @SerializedName("opening_hours") val openingHours: String,
    val description: String,
    @SerializedName("main_image_url") val mainImageUrl: String
)

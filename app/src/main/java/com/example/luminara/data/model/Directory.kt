package com.example.luminara.data.model

data class Directory(
    val id: Long,
    val name: String,
    val address: String,
    val overallRating: Float,
    val openingHours: String,
    val description: String,
    val mainImageUrl: String
)

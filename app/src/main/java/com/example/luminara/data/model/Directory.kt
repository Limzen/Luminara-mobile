package com.example.luminara.data.model

data class Directory(
    val id: Int,
    val name: String,
    val address: String,
    val overallRating: Int,
    val openingHours: String,
    val description: String,
    val mainImageUrl: String
)

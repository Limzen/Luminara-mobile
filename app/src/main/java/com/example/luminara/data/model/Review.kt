package com.example.luminara.data.model

import com.google.gson.annotations.SerializedName

data class Review(
    val id: Long = 0L,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("directory_id") val directoryId: Long,
    val rating: Float,
    @SerializedName("review_text") val reviewText: String,
    @SerializedName("users") val user: User?,
    @SerializedName("created_at") val createdAt: String = ""
)

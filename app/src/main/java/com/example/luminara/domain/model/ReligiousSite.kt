package com.example.luminara.domain.model

import android.media.Image

data class ReligiousSite(
    val id: Int,
    val name: String,
    val district: String,
    val rating: Float,
    val religion: String,
    val image: Int,
    val time: String
)

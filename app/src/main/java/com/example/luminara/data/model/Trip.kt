package com.example.luminara.data.model

import java.time.OffsetDateTime


data class Trip(
    val id: Long = 0L,
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

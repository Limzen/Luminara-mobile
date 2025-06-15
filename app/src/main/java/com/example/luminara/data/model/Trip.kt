package com.example.luminara.data.model

import com.google.firebase.Timestamp

data class Trip(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val startDate: Timestamp = Timestamp.now(),
    val endDate: Timestamp = Timestamp.now(),
)

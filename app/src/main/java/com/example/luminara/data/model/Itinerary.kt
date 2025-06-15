package com.example.luminara.data.model

data class Itinerary(
    val id: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val budget: Int,
    val googleMapLink: String,
    val locationId: String,
)
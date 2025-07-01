package com.example.luminara.data.model

import com.google.gson.annotations.SerializedName

data class Itinerary(
    val id: Long = 0L,
    @SerializedName("trip_id") val tripId: Long,
    @SerializedName("directory_id") val directoryId: Long,
    val date: String = "",
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    val budget: Float,
    @SerializedName("google_map_link") val googleMapLink: String,
    val directory: Directory? = null
)
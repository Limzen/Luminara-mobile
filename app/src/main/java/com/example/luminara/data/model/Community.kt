package com.example.luminara.data.model

import com.google.gson.annotations.SerializedName


data class Community(
    val id: Long = 0L,
    val name: String,
    @SerializedName("agama") val religion: String,
    @SerializedName("whatsapp_group_link") val whatsappLink: String,
    val subheading: List<String>,
    val content: List<String>
)


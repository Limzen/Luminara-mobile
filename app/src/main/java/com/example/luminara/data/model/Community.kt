package com.example.luminara.data.model

data class Community(
    val name: String,
    val religion: String,
    val location: String,
    val whatsappLink: String,
    val subheading: List<String>,
    val content: List<String>
)


package com.example.luminara.data.model

data class ChatRequest(
    val query: String
)

data class ChatResponse(
    val response: String? = null,
    val answer: String? = null,
    val error: String? = null
)

data class QuickAction(
    val text: String,
    val message: String
)

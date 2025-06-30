package com.example.luminara.data.model

import java.util.Date

data class ChatMessage(
    val id: Long = System.currentTimeMillis(),
    val text: String,
    val sender: MessageSender,
    val timestamp: Date = Date()
)

enum class MessageSender {
    USER, BOT
}

package com.example.luminara.ui.screens.chatbot

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminara.data.model.ChatMessage
import com.example.luminara.data.model.MessageSender
import com.example.luminara.data.model.QuickAction
import com.example.luminara.repository.ChatbotRepository
import com.example.luminara.utils.ChatbotConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Date
import javax.inject.Inject

/**
 * Data class to represent the JSON structure of the bot's response.
 * It must be annotated with @Serializable.
 */
@Serializable
data class BotResponse(
    val reply: String
    // You can add other fields from your JSON here if needed, e.g.:
    // val confidence_score: Double? = null
)

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isExtendedWait = MutableStateFlow(false)
    val isExtendedWait: StateFlow<Boolean> = _isExtendedWait.asStateFlow()

    private val _showQuickActions = MutableStateFlow(true)
    val showQuickActions: StateFlow<Boolean> = _showQuickActions.asStateFlow()

    val quickActions = ChatbotConfig.QUICK_ACTIONS.map { (text, message) ->
        QuickAction(text, message)
    }

    init {
        // Add initial bot message
        val initialMessage = ChatMessage(
            text = ChatbotConfig.INITIAL_BOT_MESSAGE,
            sender = MessageSender.BOT,
            timestamp = Date()
        )
        _messages.value = listOf(initialMessage)

        if (ChatbotConfig.DEBUG_MODE) {
            Log.d("ChatbotViewModel", "🔧 Luminara Chatbot - Initialized")
        }
    }

    fun sendMessage(messageText: String) {
        if (messageText.trim().isEmpty()) return

        _showQuickActions.value = false

        // Add user message
        val userMessage = ChatMessage(
            text = messageText,
            sender = MessageSender.USER,
            timestamp = Date()
        )

        _messages.value = _messages.value + userMessage
        _isLoading.value = true
        _isExtendedWait.value = false

        if (ChatbotConfig.DEBUG_MODE) {
            Log.d("ChatbotViewModel", "📤 Sending message: $messageText")
        }

        viewModelScope.launch {
            // Start extended wait timer
            val extendedWaitJob = launch {
                delay(30_000) // 30 seconds
                if (_isLoading.value) {
                    _isExtendedWait.value = true
                    if (ChatbotConfig.DEBUG_MODE) {
                        Log.d("ChatbotViewModel", "⏰ Extended wait triggered after 30 seconds")
                    }
                }
            }

            // Add timeout with coroutine timeout
            try {
                kotlinx.coroutines.withTimeout(310_000) { // 5.2 menit timeout
                    chatbotRepository.sendMessage(messageText)
                        .onSuccess { response -> // `response` is already the final string from repository
                            val botMessage = ChatMessage(
                                text = response, // Use the response directly
                                sender = MessageSender.BOT,
                                timestamp = Date()
                            )
                            _messages.value = _messages.value + botMessage

                            if (ChatbotConfig.DEBUG_MODE) {
                                Log.d("ChatbotViewModel", "📥 Received Response: $response")
                            }
                        }
                        .onFailure { error ->
                            val errorMessage = ChatMessage(
                                text = when {
                                    error.message?.contains("timeout", ignoreCase = true) == true -> 
                                        "⏱️ Maaf, server AI sedang lambat. Silakan coba lagi dalam beberapa saat."
                                    error.message?.contains("network", ignoreCase = true) == true -> 
                                        "🌐 Koneksi internet bermasalah. Periksa koneksi Anda."
                                    else -> ChatbotConfig.ERROR_MESSAGE
                                },
                                sender = MessageSender.BOT,
                                timestamp = Date()
                            )
                            _messages.value = _messages.value + errorMessage

                            if (ChatbotConfig.DEBUG_MODE) {
                                Log.e("ChatbotViewModel", "❌ API Error: ${error.message}")
                            }
                        }
                }
            } catch (timeoutException: kotlinx.coroutines.TimeoutCancellationException) {
                val timeoutMessage = ChatMessage(
                    text = "⏱️ Timeout: Server AI Hugging Face sedang sibuk. Silakan coba lagi dalam 1-2 menit.",
                    sender = MessageSender.BOT,
                    timestamp = Date()
                )
                _messages.value = _messages.value + timeoutMessage
                
                if (ChatbotConfig.DEBUG_MODE) {
                    Log.w("ChatbotViewModel", "⏱️ Coroutine timeout after 5.2 minutes")
                }
            }

            extendedWaitJob.cancel()
            _isLoading.value = false
            _isExtendedWait.value = false
        }
    }

    fun sendQuickAction(actionMessage: String) {
        sendMessage(actionMessage)
    }
}
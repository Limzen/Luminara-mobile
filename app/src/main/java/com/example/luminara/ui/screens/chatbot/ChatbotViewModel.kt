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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

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

        if (ChatbotConfig.DEBUG_MODE) {
            Log.d("ChatbotViewModel", "📤 Sending message: $messageText")
        }

        viewModelScope.launch {
            chatbotRepository.sendMessage(messageText)
                .onSuccess { response ->
                    val botMessage = ChatMessage(
                        text = response,
                        sender = MessageSender.BOT,
                        timestamp = Date()
                    )
                    _messages.value = _messages.value + botMessage
                    
                    if (ChatbotConfig.DEBUG_MODE) {
                        Log.d("ChatbotViewModel", "📥 Received response: $response")
                    }
                }
                .onFailure { error ->
                    val errorMessage = ChatMessage(
                        text = ChatbotConfig.ERROR_MESSAGE,
                        sender = MessageSender.BOT,
                        timestamp = Date()
                    )
                    _messages.value = _messages.value + errorMessage
                    
                    if (ChatbotConfig.DEBUG_MODE) {
                        Log.e("ChatbotViewModel", "❌ Error: ${error.message}")
                    }
                }

            _isLoading.value = false
        }
    }

    fun sendQuickAction(actionMessage: String) {
        sendMessage(actionMessage)
    }
}

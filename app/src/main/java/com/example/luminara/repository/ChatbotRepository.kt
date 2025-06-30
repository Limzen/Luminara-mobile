package com.example.luminara.repository

import android.util.Log
import com.example.luminara.data.model.ChatRequest
import com.example.luminara.data.model.ChatResponse
import com.example.luminara.data.remote.ChatbotRetrofitInstance
import com.example.luminara.utils.ChatbotConfig
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatbotRepository @Inject constructor() {

    private val chatbotApi = ChatbotRetrofitInstance.chatbotApi

    suspend fun sendMessage(message: String): Result<String> {
        return try {
            if (ChatbotConfig.DEBUG_MODE) {
                Log.d("ChatbotRepository", "🔗 Sending request to: ${ChatbotConfig.CHATBOT_API_URL}")
                Log.d("ChatbotRepository", "📝 Message: $message")
            }
            
            val request = ChatRequest(query = message)
            val response: Response<ChatResponse> = chatbotApi.sendMessage(request)
            
            if (ChatbotConfig.DEBUG_MODE) {
                Log.d("ChatbotRepository", "📊 Response code: ${response.code()}")
                Log.d("ChatbotRepository", "📊 Response success: ${response.isSuccessful}")
            }
            
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val botMessage = chatResponse?.response ?: chatResponse?.answer
                
                if (ChatbotConfig.DEBUG_MODE) {
                    Log.d("ChatbotRepository", "✅ Bot response: $botMessage")
                    Log.d("ChatbotRepository", "🔍 Raw response: $chatResponse")
                }
                
                if (botMessage != null) {
                    Result.success(botMessage)
                } else if (chatResponse?.error != null) {
                    if (ChatbotConfig.DEBUG_MODE) {
                        Log.e("ChatbotRepository", "❌ Server Error: ${chatResponse.error}")
                    }
                    Result.failure(Exception("Server Error: ${chatResponse.error}"))
                } else {
                    if (ChatbotConfig.DEBUG_MODE) {
                        Log.e("ChatbotRepository", "❌ Invalid response format")
                    }
                    Result.failure(Exception("Invalid response format from API"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                if (ChatbotConfig.DEBUG_MODE) {
                    Log.e("ChatbotRepository", "❌ API request failed with status: ${response.code()}")
                    Log.e("ChatbotRepository", "❌ Error body: $errorBody")
                }
                Result.failure(Exception("API request failed with status: ${response.code()}"))
            }
        } catch (e: Exception) {
            if (ChatbotConfig.DEBUG_MODE) {
                Log.e("ChatbotRepository", "❌ Exception: ${e.message}", e)
            }
            Result.failure(e)
        }
    }
}

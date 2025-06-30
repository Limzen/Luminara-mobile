package com.example.luminara.data.remote.api

import com.example.luminara.data.model.ChatRequest
import com.example.luminara.data.model.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatbotApiService {
    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): Response<ChatResponse>
}

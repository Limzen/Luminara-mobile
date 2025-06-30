package com.example.luminara.data.remote

import com.example.luminara.data.remote.api.ChatbotApiService
import com.example.luminara.utils.ChatbotConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatbotRetrofitInstance {
    
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ChatbotConfig.CHATBOT_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val chatbotApi: ChatbotApiService by lazy { 
        retrofit.create(ChatbotApiService::class.java)
    }
}

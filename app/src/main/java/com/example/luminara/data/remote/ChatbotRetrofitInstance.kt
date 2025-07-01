package com.example.luminara.data.remote

import com.example.luminara.data.remote.api.ChatbotApiService
import com.example.luminara.utils.ChatbotConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ChatbotRetrofitInstance {
    
    private val httpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            if (ChatbotConfig.DEBUG_MODE) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }
        
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)  // 5 menit untuk Hugging Face free tier
            .writeTimeout(300, TimeUnit.SECONDS)
            .callTimeout(300, TimeUnit.SECONDS)  // Total timeout
            .retryOnConnectionFailure(true)
            .build()
    }
    
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ChatbotConfig.CHATBOT_API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val chatbotApi: ChatbotApiService by lazy { 
        retrofit.create(ChatbotApiService::class.java)
    }
}

package com.example.luminara.di

import com.example.luminara.repository.ChatbotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatbotModule {

    @Provides
    @Singleton
    fun provideChatbotRepository(): ChatbotRepository {
        return ChatbotRepository()
    }
}

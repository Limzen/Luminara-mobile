package com.example.luminara.utils

import com.example.luminara.BuildConfig

object ChatbotConfig {
    // 🌍 API Configuration - Secure way using BuildConfig
    val CHATBOT_API_URL: String = BuildConfig.CHATBOT_API_URL
    
    // 🔑 API Key (if needed in the future)
    val API_KEY: String = BuildConfig.API_KEY
    
    // 🐛 Debug mode - based on build type
    val DEBUG_MODE: Boolean = BuildConfig.DEBUG_MODE
    
    // 📝 Default messages
    const val INITIAL_BOT_MESSAGE = "Halo! Saya adalah Luminara AI Assistant. Saya siap membantu Anda menemukan tempat ibadah, merencanakan itinerary spiritual, mencari guide, atau bergabung dengan komunitas. Ada yang bisa saya bantu hari ini? 🙏"
    const val ERROR_MESSAGE = "Maaf, saya sedang mengalami gangguan. Silakan coba lagi nanti."
    const val QUICK_ACTION_ERROR = "Maaf, terjadi kesalahan saat memproses permintaan Anda."
    
    // 🚀 Quick Actions
    val QUICK_ACTIONS = listOf(
        "🕌 Cari Tempat Ibadah" to "Saya ingin mencari tempat ibadah",
        "📅 Buat Itinerary" to "Bantuan membuat itinerary perjalanan spiritual", 
        "👨‍🏫 Cari Guide" to "Saya butuh guide untuk perjalanan spiritual",
        "👥 Bergabung Komunitas" to "Bagaimana cara bergabung dengan komunitas?"
    )
}

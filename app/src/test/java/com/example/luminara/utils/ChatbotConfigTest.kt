package com.example.luminara.utils

import com.example.luminara.BuildConfig
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit test untuk memverifikasi konfigurasi Chatbot dengan BuildConfig
 */
class ChatbotConfigTest {

    @Test
    fun testBuildConfigIntegration() {
        // Verifikasi BuildConfig fields ada dan tidak null
        assertNotNull("BuildConfig.CHATBOT_API_URL tidak boleh null", BuildConfig.CHATBOT_API_URL)
        assertNotNull("BuildConfig.API_KEY tidak boleh null", BuildConfig.API_KEY)
        
        // Verifikasi ChatbotConfig menggunakan BuildConfig
        assertEquals("ChatbotConfig harus menggunakan BuildConfig", BuildConfig.CHATBOT_API_URL, ChatbotConfig.CHATBOT_API_URL)
        assertEquals("API Key harus sama dengan BuildConfig", BuildConfig.API_KEY, ChatbotConfig.API_KEY)
        assertEquals("Debug mode harus sama dengan BuildConfig", BuildConfig.DEBUG_MODE, ChatbotConfig.DEBUG_MODE)
    }

    @Test
    fun testApiUrlConfiguration() {
        // Verifikasi API URL sudah benar
        val expectedBaseUrl = "https://bryaneugene-luminara-ai-chatbot.hf.space/"
        assertEquals("API URL harus sesuai dengan environment", expectedBaseUrl, ChatbotConfig.CHATBOT_API_URL)
        
        // Verifikasi URL tidak kosong
        assertFalse("API URL tidak boleh kosong", ChatbotConfig.CHATBOT_API_URL.isEmpty())
        
        // Verifikasi URL menggunakan HTTPS
        assertTrue("API URL harus menggunakan HTTPS", ChatbotConfig.CHATBOT_API_URL.startsWith("https://"))
        
        // Verifikasi URL berakhir dengan "/"
        assertTrue("API URL harus berakhir dengan '/'", ChatbotConfig.CHATBOT_API_URL.endsWith("/"))
    }

    @Test
    fun testSecurityConfiguration() {
        // Verifikasi BuildConfig fields tidak hardcoded dalam kode
        assertTrue("API URL harus berasal dari BuildConfig", ChatbotConfig.CHATBOT_API_URL == BuildConfig.CHATBOT_API_URL)
        
        // Verifikasi debug mode sesuai build type
        if (BuildConfig.DEBUG) {
            assertTrue("Debug mode harus aktif untuk debug build", ChatbotConfig.DEBUG_MODE)
        }
        
        // Verifikasi API key aman (kosong untuk sekarang)
        assertEquals("API key harus kosong atau aman", "", ChatbotConfig.API_KEY)
    }

    @Test
    fun testQuickActionsConfiguration() {
        // Verifikasi quick actions tidak kosong
        assertFalse("Quick actions tidak boleh kosong", ChatbotConfig.QUICK_ACTIONS.isEmpty())
        
        // Verifikasi jumlah quick actions
        assertEquals("Harus ada 4 quick actions", 4, ChatbotConfig.QUICK_ACTIONS.size)
        
        // Verifikasi semua quick actions memiliki text dan message
        ChatbotConfig.QUICK_ACTIONS.forEach { (text, message) ->
            assertFalse("Quick action text tidak boleh kosong", text.isEmpty())
            assertFalse("Quick action message tidak boleh kosong", message.isEmpty())
        }
    }

    @Test
    fun testDefaultMessages() {
        // Verifikasi initial bot message tidak kosong
        assertFalse("Initial bot message tidak boleh kosong", ChatbotConfig.INITIAL_BOT_MESSAGE.isEmpty())
        
        // Verifikasi error message tidak kosong
        assertFalse("Error message tidak boleh kosong", ChatbotConfig.ERROR_MESSAGE.isEmpty())
        
        // Verifikasi quick action error message tidak kosong
        assertFalse("Quick action error message tidak boleh kosong", ChatbotConfig.QUICK_ACTION_ERROR.isEmpty())
    }
}

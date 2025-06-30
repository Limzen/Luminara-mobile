# Luminara AI Chatbot Integration

## 📱 Fitur yang Diimplementasikan

Chatbot AI yang terintegrasi dengan API Hugging Face Space dengan fitur:

### ✨ Fitur Utama
- 💬 **Chat Interface** - UI chat yang mirip dengan aplikasi messaging modern
- 🤖 **AI Integration** - Terhubung dengan API Luminara AI Chatbot di Hugging Face
- ⚡ **Quick Actions** - Tombol cepat untuk pertanyaan umum
- 🔄 **Real-time Responses** - Respon real-time dengan loading indicator
- 📱 **Material 3 Design** - Menggunakan Material Design 3 Components

### 🚀 Quick Actions
1. 🕌 Cari Tempat Ibadah
2. 📅 Buat Itinerary 
3. 👨‍🏫 Cari Guide
4. 👥 Bergabung Komunitas

## 🏗️ Arsitektur

### 📁 Struktur File
```
app/src/main/java/com/example/luminara/
├── data/
│   ├── model/
│   │   ├── ChatMessage.kt         # Model untuk pesan chat
│   │   └── ChatModels.kt          # Model untuk request/response API
│   └── remote/
│       ├── api/
│       │   └── ChatbotApiService.kt # Interface Retrofit untuk API
│       └── ChatbotRetrofitInstance.kt # Konfigurasi Retrofit
├── repository/
│   └── ChatbotRepository.kt       # Repository pattern untuk API calls
├── ui/
│   ├── components/
│   │   ├── ChatComponents.kt      # Komponen UI untuk chat
│   │   └── ChatSuggestionCard.kt  # Card untuk quick actions
│   └── screens/chatbot/
│       ├── ChatBotScreen.kt       # Screen utama chatbot
│       └── ChatbotViewModel.kt    # ViewModel dengan StateFlow
├── utils/
│   └── ChatbotConfig.kt           # Konfigurasi API dan constants
└── di/
    └── ChatbotModule.kt           # Dependency Injection dengan Hilt
```

### 🔧 Tech Stack
- **Jetpack Compose** - UI Framework
- **Hilt** - Dependency Injection
- **Retrofit** - HTTP Client
- **StateFlow** - Reactive State Management
- **MVVM + Repository Pattern** - Architecture Pattern

## 🌐 API Configuration

API endpoint dikonfigurasi di `ChatbotConfig.kt`:

```kotlin
const val CHATBOT_API_URL = "https://bryaneugene-luminara-ai-chatbot.hf.space/"
```

### 📝 API Request/Response Format

**Request:**
```json
{
  "query": "Saya ingin mencari tempat ibadah"
}
```

**Response:**
```json
{
  "response": "Tentu! Saya dapat membantu Anda mencari tempat ibadah..."
}
```

## 🚀 Cara Menggunakan

1. **Buka aplikasi** dan navigasi ke tab Chatbot
2. **Ketik pesan** di input field bawah
3. **Tekan tombol Send** atau gunakan Quick Actions
4. **Tunggu response** dari AI assistant
5. **Lanjutkan percakapan** sesuai kebutuhan

## 🔧 Development

### Prerequisites
- Android Studio Arctic Fox atau lebih baru
- Kotlin 1.8+
- Min SDK 21
- Target SDK 35

### Setup
1. Pastikan internet permission sudah ada di `AndroidManifest.xml`
2. Network security config sudah dikonfigurasi untuk HTTPS
3. Hilt dependency injection sudah setup di `MyApplication.kt`
4. Semua dependencies sudah ada di `build.gradle.kts`

### Debug Mode
Debug logging dapat diaktifkan di `ChatbotConfig.kt`:
```kotlin
const val DEBUG_MODE = true
```

Logs akan muncul dengan tag:
- `ChatbotViewModel` - untuk aktivitas UI
- `ChatbotRepository` - untuk API calls

## 🛠️ Customization

### Mengubah API Endpoint
Edit `ChatbotConfig.kt`:
```kotlin
const val CHATBOT_API_URL = "your-api-endpoint-here"
```

### Menambah Quick Actions
Edit array di `ChatbotConfig.kt`:
```kotlin
val QUICK_ACTIONS = listOf(
    "🕌 Action Baru" to "Pesan untuk action baru",
    // ... actions lainnya
)
```

### Styling
- Colors: `ui/theme/Color.kt`
- Typography: `ui/theme/Type.kt`
- Chat bubble styling: `ui/components/ChatComponents.kt`

## 🐛 Troubleshooting

### API tidak merespon
1. Cek koneksi internet
2. Pastikan API endpoint aktif
3. Check logs dengan DEBUG_MODE = true

### Build errors
1. Clean project: Build > Clean Project
2. Rebuild: Build > Rebuild Project
3. Sync Gradle files

### Chat tidak muncul
1. Pastikan Hilt setup benar di `@AndroidEntryPoint`
2. Check ViewModel injection
3. Verify navigation setup di `NavGraphSetup.kt`

## 📱 Testing

Untuk test manual:
1. Jalankan aplikasi
2. Navigate ke Chatbot tab
3. Test Quick Actions
4. Test manual typing
5. Test dengan/tanpa koneksi internet

## 🔮 Future Enhancements

- 🎤 Voice input integration
- 📷 Image upload untuk konteks
- 💾 Chat history persistence
- 🔔 Push notifications untuk responses
- 🌐 Offline mode dengan cached responses
- 🎨 Tema custom untuk chat bubbles
- 📊 Analytics tracking untuk user interactions

---

*Dibuat untuk Luminara Mobile App - Spiritual Journey Assistant* 🙏

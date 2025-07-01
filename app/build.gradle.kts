plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("com.google.devtools.ksp")
    id ("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.luminara"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.luminara"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // 🔐 API Configuration - Secure way to store API URLs
        buildConfigField("String", "CHATBOT_API_URL", "\"https://bryaneugene-luminara-ai-chatbot.hf.space/\"")
        buildConfigField("String", "API_KEY", "\"\"")
        buildConfigField("boolean", "DEBUG_MODE", "true")
    }

    buildTypes {
        debug {
            isDebuggable = true
            // 🔧 Development configuration
            buildConfigField("String", "CHATBOT_API_URL", "\"https://bryaneugene-luminara-ai-chatbot.hf.space/\"")
            buildConfigField("String", "API_KEY", "\"\"")
            buildConfigField("boolean", "DEBUG_MODE", "true")
        }
        
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 🚀 Production configuration
            buildConfigField("String", "CHATBOT_API_URL", "\"https://bryaneugene-luminara-ai-chatbot.hf.space/\"")
            buildConfigField("String", "API_KEY", "\"\"")
            buildConfigField("boolean", "DEBUG_MODE", "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //fonts
    implementation(libs.androidx.ui.text.google.fonts)
    implementation( libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)

    //coil
    implementation(libs.coil.compose)

    //retrofit
    implementation (libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    //datastore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.runtime.compose)

}
package com.example.luminara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.luminara.ui.screens.itinerary.CreateItinerary
import com.example.luminara.ui.screens.home.HomeScreen
import com.example.luminara.ui.screens.login.LoginScreen
import com.example.luminara.ui.screens.signup.SignUpScreen
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.theme.LuminaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuminaraTheme {
                HomeScreen()
            }
        }
    }
}


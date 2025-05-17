package com.example.luminara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.theme.LuminaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuminaraTheme {
                ProfileScreen()
            }
        }
    }
}


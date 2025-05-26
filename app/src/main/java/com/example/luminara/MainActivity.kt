package com.example.luminara

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.luminara.navigation.NavGraphSetup
import com.example.luminara.navigation.Screen
import com.example.luminara.navigation.navigateToSingleTop
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.screen.PasswordManagerScreen
import com.example.luminara.ui.screens.home.GuideScreen
import com.example.luminara.ui.screens.itinerary.CreateItinerary
import com.example.luminara.ui.screens.home.HomeScreen
import com.example.luminara.ui.screens.itinerary.DetailItinerary
import com.example.luminara.ui.screens.home.SearchScreen
import com.example.luminara.ui.screens.home.SiteDetailScreen
import com.example.luminara.ui.screens.itinerary.FormItinerary
import com.example.luminara.ui.screens.login.LoginScreen
import com.example.luminara.ui.screens.profile.MyProfileScreen
import com.example.luminara.ui.screens.signup.SignUpScreen
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.theme.LuminaraTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuminaraTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {BottomBar(
                        navController = navController,
                        onNavigateToHome = { navController.navigateToSingleTop(Screen.Home)},
                        onNavigateToItinerary = { navController.navigateToSingleTop(Screen.Itinerary)},
                        onNavigateToCommunity = { navController.navigateToSingleTop(Screen.Community)},
                        onNavigateToChatbot = { navController.navigateToSingleTop(Screen.Chatbot)},
                        onNavigateToAccount = { navController.navigateToSingleTop(Screen.Account)},
                    )}
                ) { innerPadding ->
                    NavGraphSetup(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


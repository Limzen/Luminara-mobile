package com.example.luminara.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.luminara.navigation.Screen


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToItinerary: () -> Unit,
    onNavigateToCommunity: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToAccount: () -> Unit,
) {
    composable(Screen.Home.route) {
        MainScreen(
            onNavigateToHome = onNavigateToHome,
            onNavigateToItinerary = onNavigateToItinerary,
            onNavigateToCommunity = onNavigateToCommunity,
            onNavigateToChatbot = onNavigateToChatbot,
            onNavigateToAccount = onNavigateToAccount
        )
    }
}

fun NavController.navigateToMain() {
    navigate(Screen.Home.route)
}
package com.example.luminara.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.luminara.navigation.Screen


fun NavGraphBuilder.homeScreen(
    onNavigateToSearch: () -> Unit,
) {
    composable(Screen.Home.route) {
        HomeScreen(
            onNavigateToSearch = onNavigateToSearch,
        )
    }
}

fun NavController.navigateToHome() {
    navigate(Screen.Home.route)
}
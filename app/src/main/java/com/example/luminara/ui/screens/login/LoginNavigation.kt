package com.example.luminara.ui.screens.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.luminara.navigation.Screen


fun NavGraphBuilder.loginScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    composable(Screen.LoginRoute.route) {
        LoginScreen(
            onNavigateToMain = onNavigateToMain,
            onNavigateToSignUp = onNavigateToSignUp
        )
    }
}
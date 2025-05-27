package com.example.luminara.ui.screens.signup



import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.luminara.navigation.Screen


fun NavGraphBuilder.signUpScreen(
    onNavigateToMain: () -> Unit,
    onNavigateUp: () -> Unit
) {
    composable(Screen.SignUpRoute.route) {
        SignUpScreen(
            onNavigateToMain = onNavigateToMain,
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToSignUp() {
    navigate(Screen.SignUpRoute.route)
}
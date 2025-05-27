package com.example.luminara

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.screens.home.homeScreen
import com.example.luminara.ui.screens.login.LoginScreen
import com.example.luminara.ui.screens.login.loginScreen
import com.example.luminara.ui.screens.main.navigateToMain
import com.example.luminara.ui.screens.signup.navigateToSignUp
import com.example.luminara.ui.screens.signup.signUpScreen
import kotlinx.serialization.Serializable

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginRoute.route
    ) {
       loginScreen(
           onNavigateToMain = {navController.navigateToMain()},
           onNavigateToSignUp = {navController.navigateToSignUp()}
       )
        signUpScreen(
            onNavigateToMain = {navController.navigateToMain()},
            onNavigateUp = {navController.navigateUp()}
        )
        homeScreen(
            onNavigateToSearch = {}
        )
    }
}



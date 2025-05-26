package com.example.luminara.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.luminara.ui.screens.home.HomeScreen
import com.example.luminara.ui.screens.itinerary.CreateItinerary
import com.example.luminara.ui.screens.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Itinerary.route) {
            CreateItinerary()
        }
        composable(Screen.Community.route) {
            HomeScreen()
        }
        composable(Screen.Chatbot.route) {
            HomeScreen()
        }
        composable(Screen.Account.route) {
            ProfileScreen()
        }
    }
}

fun NavHostController.navigateToSingleTop(screen: Screen) {
    navigate(screen.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}


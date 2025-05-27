package com.example.luminara.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminara.navigation.NavGraphSetup
import com.example.luminara.navigation.Screen
import com.example.luminara.navigation.navigateToSingleTop
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.screens.home.homeScreen
import com.example.luminara.ui.screens.home.navigateToHome
import com.example.luminara.ui.theme.Primary

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToItinerary: () -> Unit,
    onNavigateToCommunity: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToAccount: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Primary,
        bottomBar = {BottomBar(
            hierarchy = navController.currentBackStackEntryAsState().value?.destination?.hierarchy,
            onNavigateToHome = { navController.navigateToHome()},
            onNavigateToItinerary = { navController.navigateToSingleTop(Screen.Itinerary)},
            onNavigateToCommunity = { navController.navigateToSingleTop(Screen.Community)},
            onNavigateToChatbot = { navController.navigateToSingleTop(Screen.Chatbot)},
            onNavigateToAccount = { navController.navigateToSingleTop(Screen.Account)},
        )}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeScreen(
                onNavigateToSearch = {}
            )
        }
    }
}
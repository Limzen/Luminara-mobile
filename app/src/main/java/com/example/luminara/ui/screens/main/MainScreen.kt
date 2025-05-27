package com.example.luminara.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminara.navigation.NavGraphSetup
import com.example.luminara.navigation.Screen
import com.example.luminara.navigation.allScreens
import com.example.luminara.navigation.navigateToSingleTop
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.screens.home.homeScreen
import com.example.luminara.ui.screens.home.navigateToHome
import com.example.luminara.ui.theme.Primary

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screen = allScreens.find { it.route == currentRoute } ?: Screen.Home
    val scaffoldConfig = screen.scaffoldConfig

    Scaffold(
        topBar = scaffoldConfig.topBar,
        modifier = Modifier.fillMaxSize(),
        containerColor = scaffoldConfig.backgroundColor,
        bottomBar = {
            if(screen.showBottomBar) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavGraphSetup(navController = navController, innerPadding = innerPadding)
    }
}
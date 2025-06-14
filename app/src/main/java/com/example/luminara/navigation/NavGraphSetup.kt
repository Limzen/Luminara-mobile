package com.example.luminara.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.luminara.ui.screen.PasswordManagerScreen
import com.example.luminara.ui.screens.community.CommunityDetailScreen
import com.example.luminara.ui.screens.community.CommunityScreen
import com.example.luminara.ui.screens.home.GuideScreen
import com.example.luminara.ui.screens.home.HomeScreen
import com.example.luminara.ui.screens.home.SiteDetailScreen
import com.example.luminara.ui.screens.homesearch.SearchScreen
import com.example.luminara.ui.screens.itinerary.AddItinerary
import com.example.luminara.ui.screens.itinerary.CreateItinerary
import com.example.luminara.ui.screens.itinerary.DetailItinerary
import com.example.luminara.ui.screens.itinerary.EditItinerary
import com.example.luminara.ui.screens.itinerary.FormItinerary
import com.example.luminara.ui.screens.login.LoginScreen
import com.example.luminara.ui.screens.profile.MyProfileScreen
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.screens.signup.SignUpScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController, innerPadding = innerPadding)
        }
        composable(Screen.HomeSearch.route) {
            SearchScreen(navController = navController, innerPadding = innerPadding)
        }
        composable(Screen.SiteDetail.route) {
            SiteDetailScreen(navController = navController)
        }
        composable(Screen.Guide.route) {
            GuideScreen(navController = navController)
        }


        composable(Screen.Itinerary.route) {
            CreateItinerary(navController = navController,innerPadding = innerPadding)
        }
        composable(Screen.DetailItinerary.route) {
            DetailItinerary(navController = navController)
        }
        composable(Screen.AddItinerary.route) {
            AddItinerary(navController = navController)
        }
        composable(Screen.EditItinerary.route) {
            EditItinerary(navController = navController)
        }

        composable(Screen.Community.route) {
            CommunityScreen(navController = navController)
        }
        composable(Screen.CommunityDetail.route) {
            CommunityDetailScreen(navController = navController)
        }
        composable(Screen.Chatbot.route) {

        }
        composable(Screen.Account.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.MyProfile.route) {
            MyProfileScreen(navController = navController)
        }
        composable(Screen.PasswordManager.route) {
            PasswordManagerScreen(navController)
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


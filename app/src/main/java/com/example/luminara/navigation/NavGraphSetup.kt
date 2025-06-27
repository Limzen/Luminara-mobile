package com.example.luminara.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.luminara.ui.screen.PasswordManagerScreen
import com.example.luminara.ui.screens.community.CommunityDetailScreen
import com.example.luminara.ui.screens.community.CommunityScreen
import com.example.luminara.ui.screens.home.GuideScreen
import com.example.luminara.ui.screens.home.HomeScreen
import com.example.luminara.ui.screens.home.SiteDetailScreen
import com.example.luminara.ui.screens.homesearch.SearchScreen
import com.example.luminara.ui.screens.itinerary.AddItinerary
import com.example.luminara.ui.screens.trip.AddTrip
import com.example.luminara.ui.screens.itinerary.DetailItinerary
import com.example.luminara.ui.screens.itinerary.EditItinerary
import com.example.luminara.ui.screens.trip.TripScreen
import com.example.luminara.ui.screens.login.LoginScreen
import com.example.luminara.ui.screens.chatbot.ChatBotScreen
import com.example.luminara.ui.screens.profile.MyProfileScreen
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.screens.signup.SignUpScreen
import com.example.luminara.ui.screens.trip.EditTrip

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


        composable(Screen.Trip.route) {
            TripScreen(navController = navController,innerPadding = innerPadding)
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
        composable(Screen.AddTrip.route) {
            AddTrip(navController = navController)
        }
        composable(
            route = Screen.EditTrip.route,
            arguments = listOf(navArgument("tripId") {type = NavType.LongType})
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: 0L
           EditTrip(
                navController = navController,
                tripId = tripId
            )
        }

        composable(Screen.Community.route) {
            CommunityScreen(navController = navController,innerPadding = innerPadding)
        }
        composable(Screen.CommunityDetail.route) {
            CommunityDetailScreen(navController = navController)
        }
        composable(Screen.Chatbot.route) {
            ChatBotScreen(innerPadding = innerPadding)
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


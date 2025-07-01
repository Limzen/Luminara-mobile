package com.example.luminara.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.luminara.data.datastore.UserDataStore
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
import com.example.luminara.ui.screens.community.AddCommunityScreen
import com.example.luminara.ui.screens.community.CommunityViewModel
import com.example.luminara.ui.screens.profile.MyProfileScreen
import com.example.luminara.ui.screens.profile.ProfileScreen
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.screens.register.SignUpScreen
import com.example.luminara.ui.screens.review.AddReviewScreen
import com.example.luminara.ui.screens.trip.EditTrip

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    innerPadding: PaddingValues,
    userViewModel: UserViewModel
) {
    val communityViewModel: CommunityViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, userViewModel = userViewModel)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController, innerPadding = innerPadding, userViewModel = userViewModel)
        }
        composable(
            route = Screen.HomeSearch.route,
            arguments = listOf(navArgument("query") {type = NavType.StringType})
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            Log.d("search", query)
            SearchScreen(navController = navController, innerPadding = innerPadding, query = query, userViewModel = userViewModel)
        }
        composable(
            route = Screen.SiteDetail.route,
            arguments = listOf(navArgument("id") {type = NavType.LongType})
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            SiteDetailScreen(navController = navController, id=id)
        }
        composable(
            route = Screen.AddReview.route,
            arguments = listOf(navArgument("directoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val directoryId = backStackEntry.arguments?.getLong("directoryId") ?: 0L
            AddReviewScreen(navController = navController, directoryId = directoryId, userViewModel = userViewModel)
        }
        composable(Screen.Guide.route) {
            GuideScreen(navController = navController)
        }


        composable(Screen.Trip.route) {
            TripScreen(navController = navController,innerPadding = innerPadding)
        }
        composable(
            route = Screen.DetailItinerary.route,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) {  backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: 0L
            DetailItinerary(navController = navController, tripId = tripId)
        }
        composable(
            route = Screen.AddItinerary.route,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: 0L
            AddItinerary(navController = navController, tripId = tripId)
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
            CommunityScreen(navController = navController,innerPadding = innerPadding, communityViewModel = communityViewModel)
        }
        composable(Screen.CommunityDetail.route) {
            CommunityDetailScreen(
                navController = navController,
                communityViewModel = communityViewModel
            )
        }

        composable(Screen.AddCommunity.route) {
            AddCommunityScreen(navController = navController)
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


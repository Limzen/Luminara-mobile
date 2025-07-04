package com.example.luminara.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.luminara.navigation.Screen.Home
import com.example.luminara.ui.theme.Primary
import kotlinx.serialization.Serializable

data class ScaffoldConfig(
    val topBar: @Composable () -> Unit = {},
    val backgroundColor: Color = Primary
)

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object HomeSearch: Screen("home_search/{query}") {
        fun createRoute(query:String) = "home_search/$query"
    }
    data object SiteDetail: Screen("site_detail/{id}") {
        fun createRoute(id: Long) = "site_detail/$id"
    }
    data object AddReview : Screen("add_review/{directoryId}") {
        fun createRoute(directoryId: Long) = "add_review/$directoryId"
    }
    data object Guide: Screen("guide")


    data object Trip: Screen("trip")
    data object DetailItinerary: Screen("detail_itinerary/{tripId}") {
        fun createRoute(tripId: Long) = "detail_itinerary/$tripId"
    }
    data object AddItinerary: Screen("add_itinerary/{tripId}") {
        fun createRoute(tripId: Long) = "add_itinerary/$tripId"
    }
    data object EditItinerary: Screen("edit_itinerary")
    data object AddTrip: Screen("add_trip")
    data object EditTrip: Screen("edit_trip/{tripId}") {
        fun createRoute(tripId: Long) = "edit_Trip/$tripId"
    }


    data object Community: Screen("community")
    data object CommunityDetail: Screen("community_detail")
    data object AddCommunity: Screen("add_community")

    data object Chatbot: Screen("chatbot")

    data object Account: Screen("account")
    data object MyProfile: Screen("my_profile")
    data object PasswordManager: Screen("password_manager")

    data object Login: Screen("login")
    data object SignUp: Screen("signup")
}

val allScreens = listOf(
    Home, Screen.Trip, Screen.Community,
    Screen.Chatbot, Screen.Account, Screen.HomeSearch, Screen.SiteDetail, Screen.AddReview, Screen.Guide,
    Screen.EditItinerary, Screen.DetailItinerary, Screen.AddItinerary, Screen.AddTrip, Screen.EditTrip,
    Screen.MyProfile, Screen.PasswordManager,
    Screen.CommunityDetail, Screen.AddCommunity,
    Screen.Login, Screen.SignUp
)

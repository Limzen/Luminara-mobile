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
    data object HomeSearch: Screen("home_search")
    data object SiteDetail: Screen("site_detail")
    data object Guide: Screen("guide")


    data object Itinerary: Screen("itinerary")
    data object FormItinerary: Screen("form_itinerary")
    data object DetailItinerary: Screen("detail_itinerary")


    data object Community: Screen("community")
    data object CommunityDetail: Screen("community_detail")

    data object Chatbot: Screen("chatbot")

    data object Account: Screen("account")
    data object MyProfile: Screen("my_profile")
    data object PasswordManager: Screen("password_manager")

    data object Login: Screen("login")
    data object SignUp: Screen("signup")
}

val allScreens = listOf(
    Home, Screen.Itinerary, Screen.Community,
    Screen.Chatbot, Screen.Account, Screen.HomeSearch, Screen.SiteDetail, Screen.Guide,
    Screen.FormItinerary, Screen.DetailItinerary,
    Screen.MyProfile, Screen.PasswordManager,
    Screen.CommunityDetail,
    Screen.Login, Screen.SignUp
)

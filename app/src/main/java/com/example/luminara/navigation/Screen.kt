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

sealed class Screen(val route: String, val showBottomBar: Boolean, val scaffoldConfig: ScaffoldConfig) {
    data object Home: Screen("home", true, ScaffoldConfig())
    data object HomeSearch: Screen("home_search", false, ScaffoldConfig())
    data object SiteDetail: Screen("site_detail", false, ScaffoldConfig())
    data object Guide: Screen("guide", false, ScaffoldConfig())


    data object Itinerary: Screen("itinerary", true, ScaffoldConfig(
        backgroundColor = Color.White
    ))
    data object FormItinerary: Screen("form_itinerary", false, ScaffoldConfig())
    data object DetailItinerary: Screen("detail_itinerary", false, ScaffoldConfig())


    data object Community: Screen("community", true, ScaffoldConfig())
    data object CommunityDetail: Screen("community_detail", false, ScaffoldConfig())

    data object Chatbot: Screen("chatbot", true, ScaffoldConfig())

    data object Account: Screen("account", true, ScaffoldConfig())
    data object MyProfile: Screen("my_profile", false, ScaffoldConfig())
    data object PasswordManager: Screen("password_manager", false, ScaffoldConfig())

    data object Login: Screen("login", false, ScaffoldConfig(
        backgroundColor = Color.White
    ))
    data object SignUp: Screen("signup", false, ScaffoldConfig(
        backgroundColor = Color.White
    ))
}

val allScreens = listOf(
    Home, Screen.Itinerary, Screen.Community,
    Screen.Chatbot, Screen.Account, Screen.HomeSearch, Screen.SiteDetail, Screen.Guide,
    Screen.FormItinerary, Screen.DetailItinerary,
    Screen.MyProfile, Screen.PasswordManager,
    Screen.CommunityDetail,
    Screen.Login, Screen.SignUp
)

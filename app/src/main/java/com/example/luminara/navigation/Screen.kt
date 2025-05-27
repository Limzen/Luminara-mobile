package com.example.luminara.navigation

import kotlinx.serialization.Serializable


sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Itinerary: Screen("itinerary")
    data object Community: Screen("community")
    data object Chatbot: Screen("chatbot")
    data object Account: Screen("account")
    data object LoginRoute: Screen("login")
    data object SignUpRoute: Screen("signup")
    data object SearchRoute: Screen("search")
    data object MainRoute: Screen("main")
}

package com.example.luminara.navigation

data class NavItem(
    val title: String,
    val icon: Int,
    val screen: Screen,
    val onClick: () -> Unit
)
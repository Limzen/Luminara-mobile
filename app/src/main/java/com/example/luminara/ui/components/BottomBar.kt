package com.example.luminara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.luminara.R
import com.example.luminara.navigation.NavItem
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.theme.LightBrown
import com.example.luminara.ui.theme.Primary
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy


@Composable
fun BottomBar(navController: NavController) {

    val bottomNavItems = listOf(
        NavItem("Home", R.drawable.home_icon, Screen.Home),
        NavItem("Trip", R.drawable.itinerary_icon, Screen.Trip),
        NavItem("Community", R.drawable.community_icon, Screen.Community,),
        NavItem("ChatBot", R.drawable.chatbot_icon, Screen.Chatbot),
        NavItem("Account", R.drawable.profile_icon, Screen.Account)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        tonalElevation = 4.dp,
        color = LightBrown,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            bottomNavItems.forEach {item ->
                val isSelected = currentRoute == item.screen.route
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(item.screen.route)
                        }
                        .clip(RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (isSelected) Primary else Color.Transparent,
                                shape = RoundedCornerShape(50) // Oval
                            )
                            .padding(horizontal = 12.dp, vertical = 1.dp) // Controls oval shape
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = if (isSelected) Color.White else Primary,
                            modifier = if(item.title == "Home") Modifier
                                .size(25.dp).scale(1.2f) else Modifier.size(25.dp).scale(1f)

                        )
                    }
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                }
            }
        }
    }
}
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
import androidx.navigation.NavDestination.Companion.hierarchy


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToHome: () -> Unit,
    onNavigateToItinerary: () -> Unit,
    onNavigateToCommunity: () -> Unit,
    onNavigateToChatbot: () -> Unit,
    onNavigateToAccount: () -> Unit,
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomNavItems = listOf(
        NavItem("Home", R.drawable.home_icon, Screen.Home, onNavigateToHome),
        NavItem("Itinerary", R.drawable.itinerary_icon, Screen.Itinerary, onNavigateToItinerary),
        NavItem("Community", R.drawable.community_icon, Screen.Community, onNavigateToCommunity),
        NavItem("ChatBot", R.drawable.chatbot_icon, Screen.Chatbot, onNavigateToChatbot),
        NavItem("Account", R.drawable.profile_icon, Screen.Account, onNavigateToAccount)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface(
        tonalElevation = 4.dp,
        color = LightBrown,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            bottomNavItems.forEach {item ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == item.screen::class.qualifiedName
                } == true
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            item.onClick()
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
                            modifier = Modifier.size(24.dp) // Icon size remains fixed
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
package com.example.luminara

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminara.data.datastore.UserDataStore
import com.example.luminara.navigation.NavGraphSetup
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.screens.community.CommunityTopBar
import com.example.luminara.ui.screens.home.HomeTopBar
import com.example.luminara.ui.screens.chatbot.ChatBotScreen
import com.example.luminara.ui.screens.chatbot.ChatbotTopBar
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.screens.trip.TripTopBar
import com.example.luminara.ui.theme.LuminaraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LuminaraTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf("home", "trip","community","chatbot","account")

                val context = LocalContext.current
                val userViewModel: UserViewModel = remember { UserViewModel(UserDataStore(context)) }

                Scaffold(
                    topBar = {
                        when(currentRoute) {
                            "home" -> HomeTopBar(navController = navController, userViewModel = userViewModel)
                            "trip" -> TripTopBar(navController = navController)
                            "community" -> CommunityTopBar(navController = navController)
                            "chatbot" -> ChatbotTopBar(navController = navController)
                        }
                    },
                    modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                    bottomBar = {
                        if(showBottomBar) {
                            BottomBar(navController)
                        }
                    },
                ) { innerPadding ->
                    NavGraphSetup(navController = navController, innerPadding = innerPadding, userViewModel)
                }
            }
        }
    }
}




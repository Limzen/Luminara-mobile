package com.example.luminara

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.screens.home.homeScreen
import com.example.luminara.ui.screens.login.LoginScreen
//import com.example.luminara.ui.screens.main.mainScreen
//import com.example.luminara.ui.screens.main.navigateToMain
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoot() {
   // val navController = rememberNavController()
   // NavHost(
   //     navController = navController,
  //      startDestination = Screen.LoginRoute.route
  //  ) {
     //  loginScreen(
        //   onNavigateToMain = {navController.navigateToMain()},
        //   onNavigateToSignUp = {navController.navigateToSignUp()}
     //  )
      //  signUpScreen(
         //   onNavigateToMain = {navController.navigateToMain()},
         //   onNavigateUp = {navController.navigateUp()}
     //   )
       // mainScreen(
        //    onNavigateToHome = {  },
      //  onNavigateToItinerary =  {  },
      //  onNavigateToCommunity =  {  },
     //   onNavigateToChatbot = {  },
      //  onNavigateToAccount = {  },
      //  )
    //}
}



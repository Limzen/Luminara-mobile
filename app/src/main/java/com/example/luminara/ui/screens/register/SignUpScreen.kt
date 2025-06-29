package com.example.luminara.ui.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.data.datastore.UserDataStore
import com.example.luminara.data.model.User
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.AuthButton
import com.example.luminara.ui.components.AuthTextField
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BlueText


@Composable
fun SignUpScreen (
    navController: NavController,
    userViewModel: UserViewModel
){

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Sign Up",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Username",
            style = MaterialTheme.typography.titleMedium,
        )

        AuthTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = "Username",
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Email",
            style = MaterialTheme.typography.titleMedium,
        )

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Example@gmail.com",
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.titleMedium,
        )

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "At least 8 characters",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            onClick = {
                userViewModel.register(
                    User(
                        email = email,
                        password = password,
                        username = username
                    ),
                    onSuccess = {
                        navController.navigate(Screen.Login.route)
                    },
                    onError = {}
                )
            },
            text = "Sign Up",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Sign In",
                style = MaterialTheme.typography.titleSmall,
                color = BlueText,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Login.route)
                    }
            )
        }
    }
}


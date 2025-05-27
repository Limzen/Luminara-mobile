package com.example.luminara.ui.screens.login

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.AuthButton
import com.example.luminara.ui.components.AuthTextField
import com.example.luminara.ui.theme.BlueText

@Composable
fun LoginScreen(
    navController: NavController
) {
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
            text = "Welcome 👋",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Today is a new day. It's your day. You shape it. Sign in to start managing your projects.",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Email",
            style = MaterialTheme.typography.titleMedium
        )
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Example@gmail.com",

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.titleMedium
        )
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "At least 8 characters",
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Forgot password?",
            style = MaterialTheme.typography.titleMedium,
            color = BlueText,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            onClick = { navController.navigate(Screen.Home.route) },
            text = "Login",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Don't you have an account?",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.titleSmall,
                color = BlueText,
                modifier = Modifier
                    .clickable{
                        navController.navigate(Screen.SignUp.route)
                    }
            )
        }


    }
}


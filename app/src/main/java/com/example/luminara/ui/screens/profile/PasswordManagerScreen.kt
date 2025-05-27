package com.example.luminara.ui.screen
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.luminara.R
import com.example.luminara.ui.components.AuthButton
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.ItineraryTextfield
import com.example.luminara.ui.theme.BackbuttonArrow
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerScreen() {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Password Manager",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    BackButton(
                        onClick = {},
                        modifier = Modifier.padding(start = Dimensions.OuterPadding)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(Dimensions.OuterPadding)
                .background(BackgroundColor)
        ) {
            ItineraryTextfield(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                label = "Current Password",
                placeholder = "Isi Password Lamamu",
                trailingIcon = {
                    IconButton(onClick = { currentPasswordVisible = !currentPasswordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (currentPasswordVisible) R.drawable.visibility
                                else R.drawable.visibilityoff
                            ),
                            contentDescription = "Toggle Password",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if (currentPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )

            Text(
                text = "Forgot Password?",
                color = Primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* TODO: Forgot Password */ }
            )

            // New Password
            ItineraryTextfield(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = "New Password",
                placeholder = "Masukan Password Baru",
                trailingIcon = {
                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (newPasswordVisible) R.drawable.visibility
                                else R.drawable.visibilityoff
                            ),
                            contentDescription = "Toggle Password",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))
            // Confirm Password
            ItineraryTextfield(
                value = confirmPassword,
                onValueChange = { confirmPassword= it },
                label = "Confirm Password",
                placeholder = "Masukan ulang",
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (confirmPasswordVisible) R.drawable.visibility
                                else R.drawable.visibilityoff
                            ),
                            contentDescription = "Toggle Password",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )


            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary

                )
            ) {
                Text(
                    "Change Password",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }

}
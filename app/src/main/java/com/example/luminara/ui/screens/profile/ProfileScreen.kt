package com.example.luminara.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*


import com.example.luminara.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.theme.Primary


@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Primary)

        ) {
            Text(
                text = "Profile",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-80).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.kucing),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(10.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Andry",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8B4513)
            )
            Text(
                text = "Andry123",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



        }



        ProfileMenuItem(icon = Icons.Default.Person, text = "My Profile", onClick = {navController.navigate(
            Screen.MyProfile.route)})
        ProfileMenuItem(icon = Icons.Default.Lock, text = "Password Manager", onClick = {navController.navigate(Screen.PasswordManager.route)})
        ProfileMenuItem(icon = Icons.AutoMirrored.Filled.ExitToApp, text = "Log Out", onClick = {})
    }
}





@Composable
fun ProfileMenuItem(icon: ImageVector, text: String, onClick : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF8B4513))
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF8B4513),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Primary
            )
        }
        Divider()
    }
}





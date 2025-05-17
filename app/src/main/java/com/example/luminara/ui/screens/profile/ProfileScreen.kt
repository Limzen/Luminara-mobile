package com.example.luminara.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.example.luminara.ui.theme.Primary


@Composable
fun ProfileScreen() {
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
                text = "Cristie Wanna",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8B4513)
            )
            Text(
                text = "cristiewanna123",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



        }



        ProfileMenuItem(icon = Icons.Default.Person, text = "MyProfile")
        ProfileMenuItem(icon = Icons.Default.Lock, text = "Password Manager")
        ProfileMenuItem(icon = Icons.Default.Face, text = "ChatBot")
        ProfileMenuItem(icon = Icons.Default.ExitToApp, text = "LogOut")
    }
}


@Composable
fun ProfileMenuItem(icon: ImageVector, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-70).dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
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
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color.Gray
            )
        }
        Divider()
    }
}




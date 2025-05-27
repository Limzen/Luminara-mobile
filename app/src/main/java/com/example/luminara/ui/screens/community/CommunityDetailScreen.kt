package com.example.luminara.ui.screens.community

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityDetailScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBarPadding = WindowInsets.navigationBars.asPaddingValues()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackButton(
                        onClick = {},
                        modifier = Modifier.padding(start = Dimensions.OuterPadding)
                    )
                },
                actions = {
                    Button(
                        modifier = Modifier.padding(end = Dimensions.OuterPadding),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Primary
                        ), // Removed extra comma and parenthesis
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 6.dp
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Join Group",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W700)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Primary,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Primary)
                    .padding(
                        PaddingValues(top = innerPadding.calculateTopPadding())
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.OuterPadding)
                        .padding(top = 4.dp, bottom = 12.dp)
                ) {
                    Text(
                        "Al-Mashun Community",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Location Highlight: Al-Mashun Grand Mosque",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W700),
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        "124 Active Members",
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        color = Color.White.copy(alpha = 0.8f),
                    )
                }
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    color = BackgroundColor
                ) {
                    Column(
                        modifier = Modifier
                            .padding(Dimensions.OuterPadding)
                            .padding(navBarPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Section(
                            title = "A Jewel of Medan’s Spiritual Heritage",
                            content = "Nestled in the heart of Medan, Al-Mashun Grand Mosque stands as a beacon..."
                        )

                        Section(
                            title = "A Center for Worship and Community Life",
                            content = "Beyond its architectural charm, Al-Mashun Grand Mosque is an active place of worship..."
                        )

                        Section(
                            title = "Promoting Religious Tourism with Technology",
                            content = "Visiting Al-Mashun Grand Mosque is not only a chance to witness architectural splendor...",
                            bullets = listOf(
                                "Online reservation features for group visits",
                                "Virtual and audio-guided tours",
                                "Multilingual historical information",
                                "Interactive maps and prayer schedules"
                            )
                        )

                        Section(
                            title = "Join Us in Celebrating Cultural Harmony",
                            content = "Through the Luminara initiative, we invite travelers..."
                        )
                    }
                }
                }
            }

        }

@Composable
fun Section(title: String, content: String, bullets: List<String>? = null) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF8B5E3C)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
        bullets?.forEach {
            Row(modifier = Modifier.padding(top = 6.dp)) {
                Text("• ", fontSize = 14.sp)
                Text(it, fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}
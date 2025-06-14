package com.example.luminara.ui.screens.itinerary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.domain.model.Itinerary
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItineraryTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = "Itinerary",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = OnPrimary,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary
        ),
    )
}

@Composable
fun CreateItinerary(
    navController: NavController,
    innerPadding: PaddingValues
) {
    val itineraries = listOf(
        Itinerary("Today", "Nama Itinerary", "Masjid agung"),
        Itinerary("14 Mei 2025", "Nama Itinerary", "Masjid nurul huda"),
        Itinerary("13 Mei 2025", "Nama Itinerary", "Graha Maria Annai Velangkani"),
        Itinerary("12 Mei 2025", "Nama Itinerary", "Masjid agung"),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(top = innerPadding.calculateTopPadding(), bottom = innerPadding.calculateBottomPadding())
            )
            .background(color = BackgroundColor)
    ) {
        item {
            Spacer(Modifier.height(Dimensions.TopBottomPadding))
        }
        items(itineraries) { itinerary ->
           ItineraryCard(
               navController = navController
           )
        }
        item {
            Spacer(Modifier.height(Dimensions.TopBottomPadding))
        }
    }
}

@Composable
private fun ItineraryCard(
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(horizontal = Dimensions.OuterPadding)
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        onClick = {
            navController.navigate(Screen.DetailItinerary.route)
        },
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.mosque1) ,
                contentDescription = "Location",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Medan Trip",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier =  Modifier.padding(horizontal = 16.dp)
                )
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                }
            }
            Text(
                text = "Dec 12 - Dec 14 2023 . A couple . Luxury",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}






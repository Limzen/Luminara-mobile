package com.example.luminara.ui.screens.trip

import android.util.Log
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
import androidx.compose.material.icons.outlined.Add
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.data.model.Itinerary
import com.example.luminara.data.model.Trip
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripTopBar(
    navController: NavController
) {
    TopAppBar(
        modifier = Modifier
            .shadow(
                elevation = Dimensions.TopBarElevation
            ),
        title = {
            Text(
                modifier = Modifier.padding(start = Dimensions.TopBarHorizontalPadding),
                text = "Trips",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(Dimensions.TopBarHorizontalPadding),
                onClick = { navController.navigate(Screen.AddTrip.route) }) {
                Icon(Icons.Outlined.Add, "Add")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundColor
        ),
    )
}

@Composable
fun TripScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: TripViewModel = hiltViewModel()

) {
    val tripList by viewModel.trips.collectAsState()

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
        items(tripList) { trip ->
           ItineraryCard(
               navController = navController,
               trip = trip
           )
        }
        item {
            Spacer(Modifier.height(Dimensions.TopBottomPadding))
        }
    }
}

@Composable
private fun ItineraryCard(
    navController: NavController,
    trip: Trip
) {
    val dateFormat = SimpleDateFormat("MMM-dd", Locale.getDefault())
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
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(trip.image)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = "Location",
                contentScale = ContentScale.Crop,
                modifier =  Modifier
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
                    text = trip.name,
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
                text = "${dateFormat.format(trip.startDate.toDate())} - ${dateFormat.format(trip.endDate.toDate())} . ${trip.description}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}






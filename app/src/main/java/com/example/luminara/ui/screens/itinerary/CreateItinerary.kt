package com.example.luminara.ui.screens.itinerary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.components.ListItinerary
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary


@Composable
fun CreateItinerary() {
    val itineraries = listOf(
        Itinerary("11 Mei 2025", "Nama Itinerary", "Masjid nurul huda"),
        Itinerary("12 Mei 2025", "Nama Itinerary", "Graha Maria Annai Velangkani"),
        Itinerary("13 Mei 2025", "Nama Itinerary", "Masjid agung"),
        Itinerary("Today", "Nama Itinerary", "Masjid agung"),
    )
//    val itineraries = listOf<Itinerary>()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomBar()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TRAVEL ITINERARY",
                    style = MaterialTheme.typography.titleLarge ,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                androidx.compose.foundation.Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(2.dp)
                ) {
                    drawLine(
                        color = Primary,
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                        strokeWidth = size.height
                    )
                }
            }

            // Tombol
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {},
                        modifier = Modifier.size(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = Color.White,
                        ),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Create itinerary", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(32.dp))
            }


            if (itineraries.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "haven't had a travel plan yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 18.dp)
                        .fillMaxSize()
                ) {
                    items(itineraries.size) { index ->
                        val item = itineraries[index]
                        ListItinerary(
                            onClick = {},
                            itineraryDate = item.date,
                            itineraryName = item.name,
                            destinationItinerary = item.destination
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}


// DATA SEMENTARA
data class Itinerary(
    val date: String,
    val name: String,
    val destination: String
)

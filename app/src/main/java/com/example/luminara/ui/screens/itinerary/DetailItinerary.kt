package com.example.luminara.ui.screens.itinerary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.DetailIti
import com.example.luminara.ui.theme.LightGray
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun DetailItinerary() {

    Box (

    ){ TopHeader() }

    Box(
        modifier = Modifier.padding(top = 120.dp)
    ){
        ImageHeader()
    }

    Box(
        modifier = Modifier.padding(top = 350.dp)
    ){
        Detail()
    }

}



@Composable
private fun TopHeader(){
    Box(
        modifier = Modifier
            .height(165.dp)
            .width(500.dp)
            .background(color = Primary)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp)
                .padding(horizontal = 20.dp),
        ) {
            BackButton(onClick = {})
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Road To Heaven",
                style = MaterialTheme.typography.titleLarge,
                color = OnPrimary,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
private fun ImageHeader() {
    Box(
        modifier = Modifier
            .height(240.dp)
            .width(500.dp),

    ){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.gereja_velangkani_medan),
            contentDescription = "Foto Graha Maria annai velangkanni"
        )

    }

}

@Composable
private fun Detail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = OnPrimary,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),


        ){
        LazyColumn (
            Modifier
                .fillMaxSize()
        ){
            item {
                DetailItineraryContent()
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                TravelBudgeting()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Notes()
            }
            item {
                Spacer(modifier = Modifier.height(42.dp))
            }

        }
    }
}

@Composable
private fun DetailItineraryContent() {
    Column (
        modifier = Modifier
            .padding(Dimensions.OuterPadding),

        ){
        Text("Detail Itinerary", style = MaterialTheme.typography.titleSmall, modifier =  Modifier.align(Alignment.CenterHorizontally) )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Graha Maria Annai Velangkanni", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "Icon Location",
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text("Jl. Sisingamangaraja No.61, Medan Kota District, Medan City, North Sumatra 20213", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Light)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text("Date :", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text("12 May 2025", style = MaterialTheme.typography.bodyMedium)
            }

            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .width(1.dp)
                    .height(40.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(0f, size.height),
                    strokeWidth = size.width
                )
            }


            Column (
                horizontalAlignment = Alignment.End
            ) {
                Text("Time :", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text("15:45 WIB", style = MaterialTheme.typography.bodyMedium)
            }

        }


    }
}

@Composable
private fun TravelBudgeting() {
    Column {
        Box (
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(
                    color = DetailIti,
                ),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "Travel Budgeting",
                style = MaterialTheme.typography.titleSmall,
                color = DarkText,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .width(180.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Rp. 350.000,-", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))

            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .width(180.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }
        }
    }
}

@Composable
private fun Notes() {

    Box (
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(
                color = DetailIti,
            ),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = "NOTES",
            style = MaterialTheme.typography.titleSmall,
            color = DarkText,
            fontWeight = FontWeight.Bold
        )

    }

    Spacer(modifier = Modifier.height(16.dp))

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding)
            .clip(RoundedCornerShape(10.dp))
            .background(OnPrimary)
            .border(
                width = 1.dp,
                color = LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .defaultMinSize(minHeight = 90.dp)

    ) {
        Text(
            text = "Bawalah perlengkapan ibadah dan berpakaian yang sopan saat mengunjungi lokasi ini.",
            style = MaterialTheme.typography.bodySmall,
            color = DarkText
        )
    }
}
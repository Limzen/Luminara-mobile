package com.example.luminara.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.theme.Primary

@Composable
fun HorizontalSitesCard() {
    Card(
        modifier = Modifier
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row() {
            Box(
                modifier = Modifier.weight(1.5f)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(R.drawable.mosque1),
                    contentDescription = "Sites",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(vertical = 8.dp, horizontal = 15.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Medan Grand Mosque",
                    color = Primary,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    "Medan Kota District",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall
                )
                Row() {
                    StarRating(rating = 2f)
                    Text(
                        "4.5 (32)",
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Text(
                    "Muslim",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
package com.example.luminara.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import com.example.luminara.R
import com.example.luminara.ui.theme.YellowText

@Composable
fun ReviewCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Kristin Watson",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W700)
        )

        Row {
            repeat(5) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = YellowText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }


    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "A beautiful and peaceful place in the heart of Medan. " +
                "The architecture is stunning with its blend of Middle Eastern, Indian, and Spanish styles. " +
                "I felt very welcomed by the staff and locals. A must-visit for anyone interested in religious and cultural heritage.",
        style = MaterialTheme.typography.bodySmall,
        color = Color.DarkGray
    )
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(4) {
            Image(
                painter = painterResource(R.drawable.mosque1),
                contentDescription = "Review Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Nov 09, 2022",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.labelSmall,
        color = Color.Gray
    )
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        thickness = 1.dp,
        color = DividerDefaults.color
    )
}
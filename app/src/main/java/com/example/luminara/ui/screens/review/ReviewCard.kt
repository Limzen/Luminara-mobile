package com.example.luminara.ui.screens.review

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.data.model.Review
import com.example.luminara.ui.theme.YellowText
import com.example.luminara.utils.DateUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReviewCard(
    review: Review
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)                 // Set the size of the avatar
                    .clip(CircleShape)           // Make it circular
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = review.user?.username ?: "Unknown",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${review.rating}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star",
                tint = YellowText,
                modifier = Modifier.size(20.dp)
            )
        }


    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = review.reviewText,
        style = MaterialTheme.typography.bodySmall,
        color = Color.DarkGray
    )
    Spacer(modifier = Modifier.height(8.dp))
   /* LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(8) { item ->
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
    */

    Text(
        text = DateUtils.formatDate(review.createdAt),
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
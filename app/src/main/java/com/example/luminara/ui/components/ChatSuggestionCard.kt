package com.example.luminara.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.luminara.R

@Composable
fun ChatSuggestionCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF814102), shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3C2D1E)
                )
                Text(
                    text = subtitle,
                    fontStyle = FontStyle.Italic,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.openarrow),
                contentDescription = "Open",
                tint = Color(0xFF814C1A),
                modifier = Modifier
                    .size(15.dp)
                    .offset(y = -20.dp)


            )
        }
    }
}

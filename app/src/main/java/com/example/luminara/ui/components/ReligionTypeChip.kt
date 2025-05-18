package com.example.luminara.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.Primary
import com.example.luminara.ui.theme.PrimaryHover

@Composable
fun ReligionTypeChip(
    name: String,
    image: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(PrimaryHover)
                    .clickable(onClick = onClick)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Religion Image",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = name,
                color = Primary,
                style = MaterialTheme.typography.bodySmall
            )
        }
}
package com.example.luminara.ui.components

import androidx.annotation.Size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@Composable
fun StarRating(
    rating: Float,
    starSize: Dp = 12.dp,
    starColor: Color = Color(0xFFFFC107),
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            when {
                i <= rating -> {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = starColor,
                        modifier = Modifier.size(starSize)
                    )
                }
                i - rating in 0.0..1.0 -> {
                    Box(modifier = Modifier.size(starSize)) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = starColor,
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RectangleShape)
                                .graphicsLayer {
                                    clip = true
                                    shape = RectangleShape
                                }
                                .drawWithContent {
                                    clipRect(right = size.width / 2) {
                                        this@drawWithContent.drawContent()
                                    }
                                }
                        )
                        Icon( // background empty star
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = starColor,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }
                else -> {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = starColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(starSize)
                    )
                }
            }
        }
    }
}

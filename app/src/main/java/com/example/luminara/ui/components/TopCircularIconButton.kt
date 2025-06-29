package com.example.luminara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.luminara.utils.Dimensions

@Composable
fun TopCircularIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 37.dp,
    icon: ImageVector
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(top = 37.dp)
            .size(size)
            .zIndex(10f)
            .background(color = Color.White.copy(alpha = 0.9f), shape = CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon",
            tint = Color.Black
        )
    }
}
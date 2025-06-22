package com.example.luminara.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomSheetAction(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

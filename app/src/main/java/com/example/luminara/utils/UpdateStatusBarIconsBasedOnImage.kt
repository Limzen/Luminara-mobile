package com.example.luminara.utils

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun UpdateStatusBarIconsBasedOnImage(imageBitmap: ImageBitmap) {
    val context = LocalContext.current
    val activity = context as Activity

    LaunchedEffect(imageBitmap) {
       // val palette = Palette.from(imageBitmap.asAndroidBitmap()).generate()
        //val isDark = palette.dominantSwatch?.hsl?.get(2)?.let { it < 0.5f } ?: false
        //WindowInsetsControllerCompat(activity.window, activity.window.decorView).isAppearanceLightStatusBars = !isDark
    }
}
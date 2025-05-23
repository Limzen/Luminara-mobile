package com.example.luminara.ui.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.components.Buttonback
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun SiteDetailScreen() {
    val imageHeightCollapsed = 80.dp
    val imageHeightExpanded = 250.dp
    val cornerRadius = 20.dp

    val scrollState = rememberLazyListState()

    // Total scroll including index and offset
    val scrollOffsetPx by remember {
        derivedStateOf {
            val estimatedItemHeightPx = 80 // Rough estimate
            scrollState.firstVisibleItemIndex * estimatedItemHeightPx + scrollState.firstVisibleItemScrollOffset
        }
    }

    val maxOffsetPx = with(LocalDensity.current) { (imageHeightExpanded - imageHeightCollapsed).toPx().toInt() }
    val safeScrollOffset = scrollOffsetPx.coerceIn(0, maxOffsetPx)
    val scrollOffsetDp = with(LocalDensity.current) { safeScrollOffset.toDp() }

    // Animate height based on scroll
    val imageHeightDp by animateDpAsState(
        targetValue = (imageHeightExpanded - scrollOffsetDp)
            .coerceIn(imageHeightCollapsed, imageHeightExpanded),
        label = "imageHeight"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary)
    ) {
        // Header Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeightDp)
                .padding(top = 45.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mosque1),
                contentDescription = "site detail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Back button overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = Dimensions.OuterPadding,
                        end = Dimensions.OuterPadding,
                        top = 15.dp
                    )
            ) {
                Buttonback(onClick = {})
            }
        }

        // Scrollable Content
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = imageHeightDp - cornerRadius),
            shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
            color = BackgroundColor
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.OuterPadding)
            ) {
              item {
                  TopHeader()
              }
            }
        }
    }
}

@Composable
private fun TopHeader() {
    Column() {
        Text(
            text = "Al-Mashun Grand Mosque",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location"
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Jl. Sisingamangaraja No.61, Medan Kota District, Medan City, North Sumatra 20213",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "location"
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Open 24 hours a day",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )
    }
}

@Composable
private fun DescriptionSection() {
    Column() {
    }
}

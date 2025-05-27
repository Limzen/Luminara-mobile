package com.example.luminara.ui.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun GuideScreen() {
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
                BackButton(onClick = {})
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
                    .padding(
                        bottom = 80.dp,
                        start = Dimensions.OuterPadding,
                        end = Dimensions.OuterPadding,
                        top = Dimensions.OuterPadding)
            ) {
                item {
                    ContentSection()
                }
            }
        }
    }
}

@Composable
private fun ContentSection() {
    Text(
        text = "Historical Background",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
    Spacer(Modifier.height(8.dp))
    Text(
        text = """
            Al-Mashun Grand Mosque, also known as Masjid Raya Al-Mashun, is one of the oldest and most historically significant mosques in Indonesia. Construction began in 1906 under the reign of Sultan Ma'mun Al Rashid Perkasa Alam, the Sultan of Deli, and was completed in 1909. Originally, the mosque was part of the royal palace complex and was built to reflect the grandeur and devotion of the Sultanate.
            
            The mosque was designed by a Dutch architect, Theodoor van Erp, and showcases a unique blend of Moorish, Middle Eastern, and Indian architectural styles. High-quality materials were imported from abroad—such as Italian marble, French stained glass, and German ceramic tiles—demonstrating the Sultan’s commitment to excellence.
            """.trimIndent(),
        style = MaterialTheme.typography.bodyMedium
    )
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )
    Text(
        text = "Etiquette When Visiting",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
    Spacer(Modifier.height(8.dp))
    Text(
        text = """
           Visitors are welcome to admire the beauty and sacred atmosphere of the mosque, but certain etiquette rules should be followed to ensure respect for its religious function:

    1. **Dress Modestly**  
       Both men and women should wear modest clothing. Women are encouraged to wear a headscarf, and attire should cover shoulders, arms, and legs.

    2. **Remove Shoes**  
       Shoes must be removed before entering the prayer hall. Designated areas are provided to store footwear.

    3. **Silence and Respect**  
       Maintain a quiet and respectful demeanor. The mosque is an active place of worship, so avoid loud conversations or phone use.

    4. **Follow Local Customs**  
       Respect any specific guidance provided by mosque staff or signage. During religious events or prayer times, certain areas may be restricted to worshippers only.

    5. **Avoid Physical Contact**  
       Physical interaction between male and female visitors (such as handshakes or hugs) should be avoided inside the mosque.
            """.trimIndent(),
        style = MaterialTheme.typography.bodyMedium
    )
}
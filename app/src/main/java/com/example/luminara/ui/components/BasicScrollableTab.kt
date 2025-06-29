package com.example.luminara.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun BasicScrollableTab(
    modifier: Modifier = Modifier,
    tabTitles: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    ) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(1f)
            .padding(horizontal = Dimensions.OuterPadding)
            .background(BackgroundColor)
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp, // no padding on sides
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[selectedTabIndex]
                val indicatorOffset = animateDpAsState(currentTabPosition.left).value

                Box(
                    modifier
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = indicatorOffset)
                        .width(currentTabPosition.width)
                        .height(2.dp)
                        .background(Primary, shape = RoundedCornerShape(8.dp))
                )
            },
            containerColor = BackgroundColor,
            divider = {} // no divider line at bottom
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) DarkText else Color.Gray,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                )
            }
        }
    }

}
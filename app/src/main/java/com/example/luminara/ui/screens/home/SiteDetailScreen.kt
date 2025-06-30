package com.example.luminara.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.data.model.BottomSheetAction
import com.example.luminara.data.model.Directory
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.BasicBottomSheet
import com.example.luminara.ui.components.ReviewCard
import com.example.luminara.ui.components.TopCircularIconButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.Primary
import com.example.luminara.ui.theme.YellowText
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiteDetailScreen(
    navController: NavController,
    id: Long
) {
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

    val directoryViewModel: DirectoryViewModel = viewModel()
    val directory by directoryViewModel.selectedDirectory.collectAsState()

    LaunchedEffect(id) {
        directoryViewModel.getDirectoryById(id)
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val showTitle = remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 1 ||
                    (scrollState.firstVisibleItemIndex == 1 && scrollState.firstVisibleItemScrollOffset > 0)
        }
    }

    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    val actions = listOf(
        BottomSheetAction(
            label = "Add To Trip",
            icon = Icons.Outlined.AddCircle,
            onClick = {

            }
        ),
        BottomSheetAction(
            label = "View Guide",
            icon = Icons.Outlined.Info,
            onClick = {

            }
        )
    )

    TransparentStatusBarActivity()

    if(directory == null) {
        CircularProgressIndicator()
    } else {
        Box(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            LazyColumn(
                state = scrollState
            ) {
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.mosque1),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                item {
                    Column(modifier = Modifier
                        .padding(horizontal = Dimensions.OuterPadding)
                        .padding(top = 20.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = directory!!.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = directory!!.address,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = Color.Gray,
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.DateRange,
                                contentDescription = "time"
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = directory!!.openingHours,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                color = Color.Black,
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text =  directory!!.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                        RatingSection()
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                        ReviewSection()
                    }
                }
            }
            LargeTopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = if (showTitle.value) Dimensions.TopBarElevation else 0.dp
                    ),
                title = {
                    AnimatedVisibility(visible = showTitle.value) {
                        Text(
                            text = directory!!.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                navigationIcon = {
                    if (showTitle.value) {
                        AnimatedVisibility(visible = showTitle.value) {
                            IconButton(
                                modifier = Modifier.padding(start = Dimensions.BackIconPadding),
                                onClick = {
                                    navController.popBackStack() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                },
                actions = {
                    if (showTitle.value) {
                        AnimatedVisibility(visible = showTitle.value) {
                            IconButton(
                                modifier = Modifier.padding(end = Dimensions.BackIconPadding),
                                onClick = {
                                    showSheet = true
                                    scope.launch { sheetState.show() }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.MoreVert,
                                    contentDescription = "More"
                                )
                            }
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = if (showTitle.value) BackgroundColor else Color.Transparent,
                    scrolledContainerColor = if (showTitle.value) BackgroundColor else Color.Transparent
                ),

                )
            if (!showTitle.value) {
                TopCircularIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = {
                        navController.popBackStack() },
                    modifier = Modifier.align(Alignment.TopStart).padding(start = Dimensions.OuterPadding)
                )
                TopCircularIconButton(
                    icon = Icons.Outlined.MoreVert,
                    onClick = {
                        showSheet = true
                        scope.launch { sheetState.show() }
                    },
                    modifier = Modifier.align(Alignment.TopEnd).padding(end = Dimensions.OuterPadding)
                )
            }
        }
        if(showSheet) {
            BasicBottomSheet(
                closeSheet = {
                    showSheet = false
                    scope.launch { sheetState.hide() }
                },
                sheetState = sheetState,
                actions = actions
            )
        }
    }

}

/*private fun Main() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary)
            .padding(
                PaddingValues(top = 0.dp)
            )
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
                BackButton(onClick = {navController.popBackStack()})
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
                    TopHeader(directory = directory!!)
                }
                item {
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    ButtonActionSection(onViewEthics = {navController.navigate(Screen.Guide.route)})
                }
                item {
                    Spacer(Modifier.height(16.dp))
                }
                item {
                    RatingSection()
                }
                item {
                    Spacer(Modifier.height(16.dp))
                }
                item {
                    ReviewSection()
                }
            }
        }
    }
}

 */

@Composable
private fun TopHeader(
    directory: Directory
) {
    Column() {
        Text(
            text = directory.name,
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
                Spacer(Modifier.width(8.dp))
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
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Open 24 hours a day",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Also known as the Great Mosque of Medan, Al-Mashun Grand Mosque is one of the most iconic and historic religious landmarks in North Sumatra. Built in 1906 during the reign of Sultan Ma’mun Al Rasyid Perkasa Alam, this mosque showcases a unique blend of Middle Eastern, Indian, and Spanish architectural styles. The structure features a majestic black dome, intricate stained glass, and marble imported from Italy. Located in the heart of Medan, it remains an active center for Islamic worship and cultural heritage, attracting both pilgrims and tourists year-round.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ButtonActionSection(onViewEthics : () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = {onViewEthics()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "View Ethics",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Add to itinerary",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun RatingSection() {
    val totalReviews = 273
    val averageRating = 4.5f
    val recommendedPercent = 88
    val ratings = listOf(
        5 to 160,
        4 to 50,
        3 to 30,
        2 to 20,
        1 to 13
    )
    val maxRatingCount = ratings.maxOf { it.second }

    Column() {
        Text(
            text = "Ratings",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "4.5",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star",
                tint = YellowText,
                modifier = Modifier.size(20.dp)
            )
        }
        /* Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            ) {
                ratings.forEach { (stars, count) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Text(
                            text = stars.toString(),
                            modifier = Modifier.width(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        LinearProgressIndicator(
                            progress = { count / maxRatingCount.toFloat() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = YellowText,
                            trackColor = Color.LightGray,
                            gapSize = (-15).dp,
                            drawStopIndicator = {}
                        )
                    }
                }
            }
            Column(
                modifier =  Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "4.5",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = YellowText,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "273 Reviews",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Column() {
                    Text(
                        text = "88%",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(2.dp))

                    Text(
                        text = "Recommended",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
         */
    }
}

@Composable
private fun ReviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Write a review",
                color = DarkText,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Reviews",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(8.dp))

        ReviewCard()
        ReviewCard()
    }
}

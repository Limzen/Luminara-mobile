package com.example.luminara.ui.screens.community

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.data.model.Community
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BasicScrollableTab
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.CreamyBrown
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    communityViewModel: CommunityViewModel
) {
    val communityList by communityViewModel.communities.collectAsState()

    LaunchedEffect(Unit) {
        communityViewModel.getCommunities()
    }


    var showSheet by remember { mutableStateOf(false)}
    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var selectedStatus by remember { mutableStateOf("All") }
    val filterItems = listOf(selectedStatus)


    val tabTitles = listOf("All", "Joined")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
            )
            .padding(bottom = 12.dp)
            .background(color = BackgroundColor)
    ) {
        Spacer(Modifier.height(8.dp))

        BasicScrollableTab(
            tabTitles = tabTitles,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = {it -> selectedTabIndex = it}
        )

        Spacer(modifier = Modifier.height(2.dp))
        when (selectedTabIndex) {
            0 -> {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = Dimensions.OuterPadding)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            filterItems.forEach {
                                FilterChip(text = it, onClick = {
                                    showSheet = true
                                    scope.launch { sheetState.show() }
                                })
                            }
                        }
                    }
                    items(communityList) {community ->
                        CommunityCard(community, onClick = {
                            communityViewModel.selectCommunity(community)
                            navController.navigate(Screen.CommunityDetail.route)
                        })
                        Log.d("communityview", "${communityViewModel.selectedCommunity}")
                    }

                }
                CommunityFilterBottomSheet(
                    isVisible = showSheet,
                    sheetState = sheetState,
                    onDismiss = { showSheet = false },
                    selectedStatus = selectedStatus,
                    onStatusChange = { selectedStatus = it },
                    onApply = {
                        communityViewModel.getCommunitiesByReligion(selectedStatus)
                        showSheet = false
                        scope.launch { sheetState.hide() }
                    },
                    onReset = {
                        selectedStatus = ""
                    }
                )
            }
            1 -> Text(modifier = Modifier.padding(horizontal = Dimensions.OuterPadding), text = "You haven't join any community")
        }

    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityTopBar(
    navController: NavController
) {
    TopAppBar(
        modifier = Modifier
            .shadow(
                elevation = Dimensions.TopBarElevation
            ),
        title = {
            Text(
                modifier = Modifier.padding(start = Dimensions.TopBarHorizontalPadding),
                text = "Community",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(Dimensions.TopBarHorizontalPadding),
                onClick = { navController.navigate(Screen.AddCommunity.route) {
                    launchSingleTop = true
                } }) {
                Icon(Icons.Outlined.Add, "Add")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundColor
        ),
    )
}

@Composable
private fun CommunityCard(community: Community, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(Dimensions.BoxRadius))
            .clickable { onClick() },
    ) {
            Image(
                painter = painterResource(id = R.drawable.mosque1),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            community.name,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = Color.White))
                        Modifier.height(4.dp)
                        Text(
                            "${community.religion} | International",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium, color = Color.White))
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow Forward",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }

            }
    }
}

@Composable
private fun FilterChip(text: String, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(
            text,
            color = Primary,
            style = MaterialTheme.typography.bodySmall
        ) },
        trailingIcon = {
            Icon(Icons.Outlined.ArrowDropDown,
                contentDescription = "Dropdown",
                tint = DarkBrown) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.Transparent,
            labelColor = DarkBrown,
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = DarkBrown.copy(alpha = 0.3f)
        ),
        border = AssistChipDefaults.assistChipBorder(
            enabled = true,
            borderColor = DarkBrown,
            borderWidth = 1.dp
        ))
}
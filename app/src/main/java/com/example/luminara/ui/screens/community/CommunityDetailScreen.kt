package com.example.luminara.ui.screens.community

import android.util.Log
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.size.Dimension
import com.example.luminara.R
import com.example.luminara.data.model.BottomSheetAction
import com.example.luminara.data.model.Community
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.BasicBottomSheet
import com.example.luminara.ui.components.BasicScrollableTab
import com.example.luminara.ui.components.TopCircularIconButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityDetailScreen(
    navController: NavController,
    communityViewModel: CommunityViewModel
) {
    val community = communityViewModel.selectedCommunity

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val scrollState = rememberLazyListState()
    val showTitle = remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 1 ||
                    (scrollState.firstVisibleItemIndex == 1 && scrollState.firstVisibleItemScrollOffset > 0)
        }
    }

    val showTab = remember {
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
            label = "Join Community",
            icon = Icons.AutoMirrored.Outlined.ExitToApp,
            onClick = {

            }
        ),
        BottomSheetAction(
            label = "View Members",
            icon = Icons.Outlined.Person,
            onClick = {

            }
        )
    )

    val tabTitles = listOf("Us", "Activity", "Post")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TransparentStatusBarActivity()

    if (community == null) {
        CircularProgressIndicator()
    } else {
        Box(
            modifier = Modifier.nestedScroll(
                scrollBehavior.nestedScrollConnection
            )
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
                            painter = painterResource(R.drawable.community),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                item {
                    Column(modifier = Modifier
                        .padding(horizontal = Dimensions.OuterPadding)
                        .padding(top = 16.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = community.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "124 active members",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
                if(!showTab.value) {
                    item {
                        BasicScrollableTab(
                            tabTitles = tabTitles,
                            selectedTabIndex = selectedTabIndex,
                            onTabSelected = {it -> selectedTabIndex = it}
                        )
                    }
                }
                item {
                    when (selectedTabIndex) {
                        0 -> {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = Dimensions.OuterPadding)
                            ) {
                                if(community.subheading != null) {
                                    community.subheading.forEachIndexed { index, subheading ->
                                        Section(
                                            title = subheading,
                                            content = community.content[index]
                                        )
                                    }
                                }
                            }
                        }
                        1 -> Text("Activity")
                    }

                }
            }
            Column {
                LargeTopAppBar(
                    modifier = Modifier
                        .shadow(
                            elevation = 0.dp
                        ),
                    title = {
                        AnimatedVisibility(visible = showTitle.value) {
                            Text(
                                text = "AL-Mashun Com",
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
                if(showTab.value) {
                    BasicScrollableTab(
                        tabTitles = tabTitles,
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = {it -> selectedTabIndex = it}
                    )
                }
            }

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

@Composable
fun Section(title: String, content: String, bullets: List<String>? = null) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF8B5E3C)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
        bullets?.forEach {
            Row(modifier = Modifier.padding(top = 6.dp)) {
                Text("• ", fontSize = 14.sp)
                Text(it, fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}
package com.example.luminara.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.query
import com.example.luminara.R
import com.example.luminara.data.model.Directory
import com.example.luminara.data.model.ReligionType
import com.example.luminara.data.model.Location
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.HomeFilterChip
import com.example.luminara.ui.components.HorizontalSitesCard
import com.example.luminara.ui.components.ReligionTypeChip
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.components.VerticalSitesCard
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.MiniHeading
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.ui.theme.YellowText
import com.example.luminara.utils.Dimensions

val categories = listOf(
    "All", "Most Popular", "Top Recommended", "Historical Sites",
    "Hidden Gems", "Recently Added"
)

val distance = listOf(
    "All", "1 km", "5 km", "10 km",
    "20 km", "20+ km"
)

val religionTypes = listOf<ReligionType>(
    ReligionType("All", R.drawable.all_religion),
    ReligionType("Mosque", R.drawable.mosque_chip),
    ReligionType("Church", R.drawable.church_chip),
    ReligionType("Temple", R.drawable.hindu_chip),
    ReligionType("Mandir", R.drawable.pagoda_chip),
    ReligionType("Shrine", R.drawable.shrine_chip)
)

val locations = listOf<Location>(
    Location(
        id = "12",
        name = "hello",
        district = "hello",
        rating = 4.5f,
        religion = "hello",
        image = "hello",
        time = "hello"
    ),
    Location(
        id = "12",
        name = "hello",
        district = "hello",
        rating = 4.5f,
        religion = "hello",
        image = "hello",
        time = "hello"
    ),
    Location(
        id = "12",
        name = "hello",
        district = "hello",
        rating = 4.5f,
        religion = "hello",
        image = "hello",
        time = "hello"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    navController: NavController,
    userViewModel: UserViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val currentUser by userViewModel.currentUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, start = Dimensions.OuterPadding, end = Dimensions.OuterPadding)
    ) {
        TopAppBar(
            title = {
                currentUser?.let { user ->
                    Text(
                        text = "Hi, ${user.username}!",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Lang")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundColor
            ),
        )
        SearchTextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            placeholder = "Where to go?",
            onClick = {
                if(searchQuery.isNotBlank()) {
                    navController.navigate(Screen.HomeSearch.createRoute(searchQuery))
                }
            }
        )
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    userViewModel: UserViewModel
) {
    val directoryViewModel: DirectoryViewModel = viewModel()
    val directories by directoryViewModel.directories.collectAsState()

    LaunchedEffect(Unit) {
        directoryViewModel.fetchDirectories()
    }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(top = innerPadding.calculateTopPadding(), bottom = innerPadding.calculateBottomPadding())
                )
                .background(color = BackgroundColor)
        )
        {
            item {
                Spacer(Modifier.height(Dimensions.TopBottomPadding))
            }
            item{
                FilterSection(title = "Discover Category", list = categories)
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
            }
            item{
                FilterSection(title = "Distance", list = distance)
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
            }
            item{
                ReligionTypeSection(list = religionTypes)
            }
            item{
                Spacer(modifier = Modifier.height(12.dp))
            }
            item{
                PopularSection(navController = navController, directories = directories)
            }
            item{
                Spacer(modifier = Modifier.height(12.dp))
            }
            item{
                RecommendedSection()
            }
            item {
                Spacer(Modifier.height(Dimensions.TopBottomPadding))
            }
        }

}


@Composable
private fun TopHeader(
    searchQuery: String,
    onSearchQuery: (String) -> Unit,
    navController: NavController
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Primary)
                .padding(horizontal = Dimensions.OuterPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hi, Cristie",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimary
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = YellowText
                        )
                        Text("My Location", fontSize = 14.sp, color = YellowText)
                    }
                }
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Lang")
            }
            Spacer(modifier = Modifier.height(10.dp))
            SearchTextField(
                value = searchQuery,
                onValueChange = onSearchQuery,
                placeholder = "Where to go?",
                onClick = {navController.navigate(Screen.HomeSearch.route)}
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 125.dp, start = Dimensions.OuterPadding, end = Dimensions.OuterPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_img),
                contentDescription = "Overlapping image",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = Color.White.copy(alpha = 0.5f),
                    blendMode = BlendMode.Lighten
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = "Explore the Heart of Faith in Medan",
                color = Primary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
private fun FilterSection(title : String, list: List<String>) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimensions.OuterPadding)
            .fillMaxWidth()
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = MiniHeading
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            list.forEach { category ->
                HomeFilterChip(text = category)
            }
        }
    }
}

@Composable
private fun ReligionTypeSection(list: List<ReligionType>) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimensions.OuterPadding)
            .fillMaxWidth()
    ) {
        Text(
            "Religions",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = MiniHeading
        )
        Spacer(Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.Start
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            list.forEach { religion ->
                ReligionTypeChip(
                    name = religion.name,
                    image = religion.icon,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

}

@Composable
private fun PopularSection(
    navController: NavController,
    directories: List<Directory>
) {
    println("directory $directories")
    Column(
        modifier = Modifier
            .padding(horizontal = Dimensions.OuterPadding)
    ) {
        HomeMediumHeading(title = "Most Popular")
        Spacer(Modifier.height(5.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            directories.forEach { site ->
                item {
                    VerticalSitesCard(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun RecommendedSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimensions.OuterPadding)
    ) {
        HomeMediumHeading(title="Top Recommended")
        Spacer(Modifier.height(5.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            locations.forEach { site ->
                HorizontalSitesCard()
            }
        }
    }
}

@Composable
private fun HomeMediumHeading(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MiniHeading
        )
        Text(
            "See All",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {},
            style = MaterialTheme.typography.bodyMedium,
            color = Primary
        )
    }
}
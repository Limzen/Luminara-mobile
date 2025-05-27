package com.example.luminara.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminara.R
import com.example.luminara.domain.model.ReligionType
import com.example.luminara.domain.model.ReligiousSite
import com.example.luminara.navigation.NavItem
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.components.HomeFilterChip
import com.example.luminara.ui.components.HorizontalSitesCard
import com.example.luminara.ui.components.ReligionTypeChip
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.components.VerticalSitesCard
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

val religiousSites = listOf<ReligiousSite>(
    ReligiousSite(id = 1, name = "Medan Grand Mosque", district = "Medan Kota District", rating = 4.5f, religion = "Muslim", image = R.drawable.mosque1, time = "07:00-19.30"),
    ReligiousSite(id = 2, name = "Masjid Al Osmani", district = "Medan Labuhan District", rating = 4f, religion = "Muslim",image = R.drawable.mosque1,time = "07:00-19.30"),
    ReligiousSite(id = 3, name = "Medan Grand Mosque", district = "Medan Kota District", rating = 4.5f, religion = "Muslim",image = R.drawable.mosque1,time = "07:00-19.30"),
    ReligiousSite(id = 4, name = "Medan Grand Mosque", district = "Medan Kota District", rating = 4.5f, religion = "Muslim",image = R.drawable.mosque1,time = "07:00-19.30"),
)


@Composable
fun HomeScreen(
    onNavigateToSearch : () -> Unit
) {
    val navController = rememberNavController()
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Primary,
        bottomBar = {
            BottomBar(
                hierarchy = navController.currentBackStackEntryAsState().value?.destination?.hierarchy,
                onNavigateToHome = {},
                onNavigateToItinerary = {},
                onNavigateToCommunity = {},
                onNavigateToChatbot = {},
                onNavigateToAccount = {},
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BackgroundColor)
                .padding(paddingValues)
        )
        {
            item{
                TopHeader(
                    searchQuery = searchQuery,
                    onSearchQuery = {searchQuery = it}
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
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
                PopularSection()
            }
            item{
                Spacer(modifier = Modifier.height(12.dp))
            }
            item{
                RecommendedSection()
            }
            item{
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

}


@Composable
private fun TopHeader(
    searchQuery: String,
    onSearchQuery: (String) -> Unit
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
            SearchTextField(value = searchQuery, onValueChange = onSearchQuery, placeholder = "Where to go?")
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
private fun PopularSection() {
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
            religiousSites.forEach { site ->
                item {
                    VerticalSitesCard()
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
            religiousSites.forEach { site ->
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
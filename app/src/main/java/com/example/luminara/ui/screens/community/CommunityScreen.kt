package com.example.luminara.ui.screens.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.luminara.R
import com.example.luminara.domain.model.Community
import com.example.luminara.ui.components.BottomBar
import com.example.luminara.ui.components.SearchResult
import com.example.luminara.ui.theme.BackbuttonArrow
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.CreamyBrown
import com.example.luminara.ui.theme.DetailIti
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    val navController = rememberNavController()
    val filterItems = listOf("Agama", "Kegiatan", "Lokasi")
    val communityList = listOf(
        Community("Al-Mashun Community", R.drawable.mosque1),
        Community("Velangkanni Faith Walkers", R.drawable.mosque1),
        Community("Spirit of Gunung Timur", R.drawable.mosque1),
        Community("Maitreya Devotees Circle", R.drawable.mosque1),
        Community("Al-Mashun Community", R.drawable.mosque1),
        Community("Velangkanni Faith Walkers", R.drawable.mosque1),
        Community("Spirit of Gunung Timur", R.drawable.mosque1),
        Community("Maitreya Devotees Circle", R.drawable.mosque1)
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Community",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = { BottomBar(
            hierarchy = navController.currentBackStackEntryAsState().value?.destination?.hierarchy,
            onNavigateToHome = {},
            onNavigateToItinerary = {},
            onNavigateToCommunity = {},
            onNavigateToChatbot = {},
            onNavigateToAccount = {},
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Primary)
                .padding(innerPadding)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = BackgroundColor
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = Dimensions.OuterPadding, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        filterItems.forEach {
                            FilterChip(text = it)
                        }
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp),
                            tint = Color.DarkGray
                        )
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(Dimensions.OuterPadding)
                    ) {
                        items(communityList) {community ->
                            CommunityCard(community)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CommunityCard(community: Community) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = CreamyBrown
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = community.image),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    community.title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "View Group",
                        style = MaterialTheme.typography.bodySmall,
                        color = Primary
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow Forward",
                        modifier = Modifier.size(16.dp),
                        tint = Primary
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterChip(text: String) {
    AssistChip(
        onClick = { /* TODO */ },
        label = { Text(
            text,
            color = Primary,
            style = MaterialTheme.typography.bodySmall
        ) },
        leadingIcon = {
            Icon(Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                tint = DetailIti) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.Transparent,
            labelColor = DetailIti,
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = DetailIti.copy(alpha = 0.3f)
        ),
        border = AssistChipDefaults.assistChipBorder(
            enabled = true,
            borderColor = DetailIti,
            borderWidth = 1.dp
        ))
}
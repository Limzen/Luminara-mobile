package com.example.luminara.ui.screens.homesearch

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.luminara.data.model.Directory
import com.example.luminara.data.model.Location
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.HorizontalCard
import com.example.luminara.ui.components.SearchResult
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.screens.home.DirectoryViewModel
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.ui.theme.YellowText
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    query: String,
    userViewModel: UserViewModel
) {
    val directoryViewModel: DirectoryViewModel = viewModel()
    val results by directoryViewModel.searchResults.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(results) {
        Log.d("search", "$results")
    }


    TransparentStatusBarActivity()

    Column {

            TopAppBar(
                title = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(start = Dimensions.BackIconPadding),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor
                ),
            )
            SearchTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Where to go?",
                onClick = {
                    directoryViewModel.searchDirectories(searchQuery)
                }
            )
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.padding(horizontal = Dimensions.OuterPadding)
        ) {
            items(results) {result ->
                HorizontalCard(directory = result, onClick = {
                    navController.navigate(Screen.SiteDetail.createRoute(result.id))
                }, imageHeight = 220.dp)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}


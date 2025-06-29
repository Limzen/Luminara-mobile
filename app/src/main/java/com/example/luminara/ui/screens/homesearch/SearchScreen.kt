package com.example.luminara.ui.screens.homesearch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.data.model.Location
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.SearchResult
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.screens.home.DirectoryViewModel
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun SearchScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    query: String
) {
    val directoryViewModel: DirectoryViewModel = viewModel()
    val results by directoryViewModel.searchResults.collectAsState()

    LaunchedEffect(query) {
        directoryViewModel.searchDirectories(query)
        Log.d("search", "$results")
    }

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

    var searchQuery by remember { mutableStateOf("") }

//    LaunchedEffect(searchQuery) {
//        snapshotFlow { searchQuery }
//            .debounce(300)
//            .distinctUntilChanged()
//            .collect { query ->
//                if (query.length >= 3) {
//                    //execute search
//                }
//            }
//    }
    Column(
        modifier = Modifier.Companion.fillMaxSize()
            .padding(
                PaddingValues(top = innerPadding.calculateTopPadding())
            )
            .background(color = Primary)
    ) {
        Row(
            modifier = Modifier.Companion.fillMaxWidth()
                .background(color = Primary)
                .padding(horizontal = Dimensions.OuterPadding)
        ) {
            BackButton(onClick = {navController.popBackStack()})
            Spacer(Modifier.Companion.width(10.dp))
            SearchTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Where to go?",
                onClick = {}
            )
        }
        Spacer(Modifier.Companion.height(12.dp))
        Surface(
            modifier = Modifier.Companion
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = BackgroundColor
        ) {
            LazyColumn(
                modifier = Modifier.Companion.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(Dimensions.OuterPadding)
            ) {
                /* items(items = locations) { site ->
                    SearchResult(site = site)
                }*/
                items(results) {directory ->
                    Text(directory.name)
                }
            }
        }

    }
}
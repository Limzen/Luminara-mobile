package com.example.luminara.ui.screens.homesearch

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.domain.model.ReligiousSite
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.SearchResult
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun SearchScreen(
    navController: NavController,
    innerPadding: PaddingValues
) {
    val religiousSites = listOf<ReligiousSite>(
        ReligiousSite(
            id = 1,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 2,
            name = "Masjid Al Osmani",
            district = "Medan Labuhan District",
            rating = 4f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 3,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
        ),
        ReligiousSite(
            id = 4,
            name = "Medan Grand Mosque",
            district = "Medan Kota District",
            rating = 4.5f,
            religion = "Muslim",
            image = R.drawable.mosque1,
            time = "07:00-19.30"
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
                items(items = religiousSites) { site ->
                    SearchResult(site = site)
                }
            }
        }

    }
}
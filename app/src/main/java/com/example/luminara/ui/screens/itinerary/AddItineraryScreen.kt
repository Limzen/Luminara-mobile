package com.example.luminara.ui.screens.itinerary

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.data.model.Directory
import com.example.luminara.data.model.Itinerary
import com.example.luminara.ui.components.BottomButton
import com.example.luminara.ui.screens.trip.TripViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItinerary(
    navController: NavController,
    tripId: Long
) {
    TransparentStatusBarActivity()

    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }
    var budget by rememberSaveable { mutableStateOf("") }
    var selectedDirectoryId by rememberSaveable { mutableStateOf(0L) }

    val itineraryViewModel: ItineraryViewModel = viewModel()
    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(BackgroundColor)
        ) {
            TopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = Dimensions.TopBarElevation
                    ),
                title = {
                    Text(
                        text = "Add Itinerary",
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
            FormItinerary(
                dateInput = date,
                onDateChange = { date = it },
                timeInput = time,
                onTimeChange = { time = it },
                budgetInput = budget,
                onBudgetChange = { budget = it },
                selectedDirectoryId = selectedDirectoryId,
                onSelectedDirectoryIdChange = { selectedDirectoryId = it }
            )
        }
        BottomButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Add Itinerary",
            onClick = {

                val itinerary = Itinerary(
                    tripId = tripId,
                    directoryId = selectedDirectoryId,
                    date = date,
                    endTime = time,
                    startTime = time,
                    budget = budget.toFloatOrNull() ?: 0f,
                    googleMapLink = "mapLink",
                )
                val gson = Gson()
                Log.d("Payload", gson.toJson(itinerary))
                itineraryViewModel.createItinerary(itinerary = itinerary, onSuccess = {
                    Log.d("itinerary success", "$itinerary")
                    navController.popBackStack()
                }, onError = {})
            }
        )
    }
}


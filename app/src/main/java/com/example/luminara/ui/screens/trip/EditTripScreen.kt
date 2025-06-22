package com.example.luminara.ui.screens.trip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.ui.components.BottomButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTrip(
    navController: NavController,
    tripId: Long,
) {
    val tripViewModel: TripViewModel = viewModel()
    val trip by tripViewModel.selectedTrip.collectAsState()

    LaunchedEffect(tripId) {
        tripViewModel.fetchTripById(tripId)
    }

    if (trip == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun showSnackbar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }


    var name by remember { mutableStateOf(trip!!.name) }
    var description by remember { mutableStateOf(trip!!.description) }
    var startDate by remember { mutableStateOf(trip!!.startDate) }
    var endDate by remember { mutableStateOf(trip!!.endDate) }

    TransparentStatusBarActivity()

    Box {
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
                        text = "Edit Trip",
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
            FormTrip(
                name = name,
                onNameChange  = {name = it},
                description = description,
                onDescriptionChange = {description = it},
                startDate = startDate,
                onStartDateChange = {startDate = it},
                endDate = endDate,
                onEndDateChange = {endDate = it},
                imageUri = "imageUri"
            )
        }
        SnackbarHost(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 22.dp),
            hostState = snackbarHostState,
        )
        BottomButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Save Changes",
            onClick = {
                if (name.isBlank()) {
                    showSnackbar(message = "Please filled in name")
                } else {
                    val updatedTrip = trip!!.copy(
                        name = name,
                        description = description,
                        startDate = startDate,
                        endDate = endDate
                    )
                    tripViewModel.editTrip(
                        updatedTrip,
                        onSuccess = {
                            showSnackbar("Trip updated")
                            navController.popBackStack()
                        },
                        onError = {
                            showSnackbar("Failed to update trip")
                        }
                    )
                }
            }
        )

    }

}
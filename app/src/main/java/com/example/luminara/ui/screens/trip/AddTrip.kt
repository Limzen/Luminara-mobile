package com.example.luminara.ui.screens.trip

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.luminara.ui.components.BottomButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.CreamyBrown
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrip(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun showSnackbar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val currentDate = System.currentTimeMillis()
    var startDate by rememberSaveable { mutableStateOf(currentDate) }
    var endDate by rememberSaveable { mutableStateOf(currentDate) }
    var imageUri by rememberSaveable { mutableStateOf("") }


    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it.toString()
    }
    val uploading by viewModel.uploading.collectAsState()


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
                        text = "Add Trip",
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
                imageUri = imageUri
            )
        }
        SnackbarHost(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 22.dp),
            hostState = snackbarHostState,
        )
        BottomButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Add Trip",
            onClick = {
                if (name.isBlank()) {
                    showSnackbar(message = "Please filled in name")
                } else {
                    viewModel.addTrip(
                        imageUri = null,
                        name = name,
                        description = description,
                        startDate = startDate,
                        endDate = endDate,
                        onSuccess = {
                            showSnackbar(message = "Trip Added")
                            navController.popBackStack()
                        },
                        onError = {
                            showSnackbar(message = "Failed: $it")
                        }
                    )
                }
            }
        )

    }
}
package com.example.luminara.ui.screens.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.data.model.Community
import com.example.luminara.data.model.Trip
import com.example.luminara.ui.components.BottomButton
import com.example.luminara.ui.screens.trip.FormTrip
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCommunityScreen(
    navController: NavController
) {
    val communityViewModel: CommunityViewModel = viewModel()

    var name by rememberSaveable { mutableStateOf("") }
    val religions = listOf<String>("Buddha", "Islam", "Kristen", "Katolik", "Hindu")
    var selectedReligion by rememberSaveable { mutableStateOf(religions[0]) }
    var whatsappLink by rememberSaveable { mutableStateOf("") }
    var subheading by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    var showSheet by remember { mutableStateOf(false)}
    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

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
                        text = "Add Community",
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
            FormCommunity(
                name = name,
                onNameChange  = {name = it},
                selectedReligion = selectedReligion,
                onSelectedReligionChange = {
                    selectedReligion = it
                    showSheet = false
                    scope.launch { sheetState.hide()}
                                           },
                onShowSheet = {
                    showSheet = true
                    scope.launch { sheetState.show() }
                              },
                onHideSheet = {
                    showSheet = false
                    scope.launch { sheetState.hide() }
                },
                sheetState = sheetState,
                showSheet = showSheet,
                religions = religions,
                whatsappLink = whatsappLink,
                onwhatsappLinkChange = {whatsappLink = it},
                subheading = subheading,
                onSubheadingChange = {subheading = it},
                content = content,
                onContentChange = {content = it}
            )
        }
        BottomButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Add Community",
            onClick = {
                if (name.isBlank()) {
                    // showSnackbar(message = "Please filled in name")
                } else {
                    communityViewModel.addCommunity(
                        Community(
                            name = name,
                            religion = selectedReligion,
                            whatsappLink = whatsappLink,
                            subheading = subheading.split(',').map{it.trim()},
                            content = content.split(',').map{it.trim()},
                        ),
                        onSuccess = {
                            navController.popBackStack()
                        },
                        onError =  {
                        }
                    )
                }
            }
        )
    }
}
package com.example.luminara.ui.screens.itinerary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.data.model.Directory
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.DatePickerField
import com.example.luminara.ui.components.ItineraryTextfield
import com.example.luminara.ui.screens.home.DirectoryViewModel
import com.example.luminara.ui.theme.BackbuttonArrow
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import java.util.*
import kotlin.collections.find
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormItinerary(
    dateInput: String,
    onDateChange: (String) -> Unit,
    timeInput: String,
    onTimeChange: (String) -> Unit,
    budgetInput: String,
    onBudgetChange: (String) -> Unit,
    selectedDirectoryId: Long,
    onSelectedDirectoryIdChange: (Long) -> Unit
) {
    DestinationSection(selectedDirectoryId = selectedDirectoryId, onSelectedDirectoryIdChange = onSelectedDirectoryIdChange)
    DateSection(dateInput, onDateChange)
    TimeSection(timeInput, onTimeChange)
    BudgetSection(budgetInput, onBudgetChange)
}

@Composable
private fun DestinationSection(selectedDirectoryId : Long, onSelectedDirectoryIdChange: (Long) -> Unit) {
    val directoryViewModel: DirectoryViewModel = viewModel()
    val directories by directoryViewModel.directories.collectAsState()

    LaunchedEffect(Unit) {
        directoryViewModel.fetchDirectories()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Destination",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
        }
        Spacer(Modifier.height(8.dp))
        DropdownMenuBox(
            items = directories,
            selectedId = selectedDirectoryId,
            onSelectedIdChange = onSelectedDirectoryIdChange
        )

    }
}

@Composable
private fun DateSection(dateInput: String, onDateChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        DatePickerField(
            label = "Date",
            date = dateInput,
            onDateChange = onDateChange
        )
    }
}

@Composable
private fun TimeSection(timeInput: String, onTimeChange: (String) -> Unit) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.DateRange, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Time",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
        }
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = timeInput,
            onValueChange = onTimeChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            singleLine = true,
            decorationBox = { innerTextField ->
                Column {
                    Box(Modifier.padding(bottom = 4.dp)) {
                        innerTextField()
                    }

                    // Underline
                    HorizontalDivider(
                        color = if (isFocused) DarkBrown else Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        )
    }
}

@Composable
private fun BudgetSection(budgetInput: String, onBudgetChange: (String) -> Unit) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Budget",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
        }
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = budgetInput,
            onValueChange = onBudgetChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            singleLine = true,
            decorationBox = { innerTextField ->
                Column {
                    Box(Modifier.padding(bottom = 4.dp)) {
                        innerTextField()
                    }

                    // Underline
                    HorizontalDivider(
                        color = if (isFocused) DarkBrown else Color.Gray,
                        thickness = 1.dp
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun DropdownMenuBox(
    items: List<Directory>,
    selectedId: Long,
    onSelectedIdChange: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selected = items.find { it.id == selectedId }

    Box {
        OutlinedTextField(
            value = selected?.name ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Directory") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", Modifier.clickable { expanded = true })
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, containerColor = Color.White) {
            items.forEach { dir ->
                DropdownMenuItem(
                    text = { Text(dir.name) },
                    onClick = {
                        onSelectedIdChange(dir.id)
                        expanded = false
                    }
                )
            }
        }
    }
}

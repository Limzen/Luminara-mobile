package com.example.luminara.ui.screens.itinerary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.components.ItineraryTextfield
import com.example.luminara.ui.theme.BackbuttonArrow
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormItinerary() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var itineraryName by remember { mutableStateOf("") }
    var itineraryDestination by remember { mutableStateOf("") }
    var itineraryBudget by remember { mutableStateOf("") }
    var itineraryNote by remember { mutableStateOf("") }
    var itineraryDate by remember { mutableStateOf("") }
    var itineraryTime by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val destinationOptions = listOf("Borobudur", "Vihara Dharma Bhakti", "Bali", "Lumbini Park", "Prambanan")


    // Date Picker Dialog
    // AKU MASI BLM NGERTI CARA MAKE NYA
//    val datePickerDialog = DatePickerDialog(
//        context,
//        { _, year, month, dayOfMonth ->
//            itineraryDate = "$dayOfMonth/${month + 1}/$year"
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH),
//    )

//    // Time Picker Dialog
//    val timePickerDialog = TimePickerDialog(
//        context,
//        { _, hourOfDay, minute ->
//            itineraryTime = String.format("%02d:%02d", hourOfDay, minute)
//        },
//        calendar.get(Calendar.HOUR_OF_DAY),
//        calendar.get(Calendar.MINUTE),
//        true
//    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .width(500.dp)
                .background(color = Primary)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp)
                    .padding(horizontal = 30.dp),
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackbuttonArrow,
                        contentColor = Color.White,
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 6.dp
                    ),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "ArrowLeft",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "New Itinerary",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-20).dp)
                .background(
                    color = OnPrimary,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Plan Your Spiritual Journey",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(24.dp))

                ItineraryTextfield(
                    value = itineraryName,
                    onValueChange = { itineraryName = it },
                    placeholder = "Example: Road to Vihara",
                    label = "Itinerary Name",
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Dropdown untuk destinasi
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Destinations / Places to Visit",
                        style = MaterialTheme.typography.titleMedium,
                        color = Primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.fillMaxWidth(),

                    ) {
                        OutlinedTextField(
                            value = itineraryDestination,
                            onValueChange = { itineraryDestination = it },
                            placeholder = { Text("Select your destination") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .height(55.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(5.dp),
                            singleLine = true,
                            readOnly = true,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = OnPrimary,
                                focusedTextColor = DarkText,
                                focusedContainerColor = OnPrimary,
                                unfocusedTextColor = DarkText,
                                focusedIndicatorColor = Primary,
                                unfocusedIndicatorColor = Primary
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            shape = RoundedCornerShape(5.dp),
                            border = BorderStroke(1.dp, Primary),
                            containerColor = OnPrimary,
                        ) {
                            destinationOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            option,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = DarkText
                                        )
                                    },
                                    onClick = {
                                        itineraryDestination = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    ItineraryTextfield(
                        value = itineraryDate,
                        onValueChange = { itineraryDate = it },
                        placeholder = "Date",
                        label = "Travel Dates",
                        singleLine = true,
                        modifier = Modifier.width(150.dp),
                        trailingIcon = {
                            Button(
                                onClick = {/* datePickerDialog.show()*/ },
                                modifier = Modifier.size(30.dp),
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OnPrimary,
                                    contentColor = Primary,
                                )
                            ) {
                                Icon(Icons.Filled.DateRange, contentDescription = "Date")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    ItineraryTextfield(
                        value = itineraryTime,
                        onValueChange = { itineraryTime = it },
                        placeholder = "Time",
                        label = "Time",
                        singleLine = true,
                        modifier = Modifier.width(160.dp),
                        trailingIcon = {
                            Button(
                                onClick = { /*timePickerDialog.show() */},
                                modifier = Modifier.size(30.dp),
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OnPrimary,
                                    contentColor = Primary,
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.nestlock),
                                    contentDescription = "Time"
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                ItineraryTextfield(
                    value = itineraryBudget,
                    onValueChange = { itineraryBudget = it },
                    placeholder = "Type your Budget (Numbers Only)",
                    label = "Estimated Budget",
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                ItineraryTextfield(
                    value = itineraryNote,
                    onValueChange = { itineraryNote = it },
                    placeholder = "Add Your Notes Here...........",
                    label = "Special Notes",
                    modifier = Modifier.height(130.dp),
                    singleLine = false
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = OnPrimary,
                        )
                    ) {
                        Text(
                            "Create itinerary",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

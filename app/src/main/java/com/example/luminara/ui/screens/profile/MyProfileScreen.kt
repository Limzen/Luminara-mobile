package com.example.luminara.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange

import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.luminara.R
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.components.AuthButton
import com.example.luminara.ui.components.AuthTextField
import com.example.luminara.ui.components.ItineraryTextfield
import com.example.luminara.ui.theme.BackbuttonArrow
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    navController: NavController
) {
    // Form Inputs
    var name by remember { mutableStateOf("Cristie Wanna") }
    var religion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("Cristiewanna123@Gmail.Com") }
    var contactNumber by remember { mutableStateOf("081390001234") }
    var gender by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("October 28, 2003") }

    val religionOptions = listOf("Islam", "Kristen", "Katolik", "Hindu", "Buddha")
    val genderOptions = listOf("Laki-laki", "Perempuan")

    var expandedReligion by remember { mutableStateOf(false) }
    var expandedGender by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                   BackButton(
                       onClick = {navController.popBackStack()},
                       modifier = Modifier.padding(start = Dimensions.OuterPadding)
                   )
                },
                actions = {
                    Button(
                        modifier = Modifier.padding(end = Dimensions.OuterPadding),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BackbuttonArrow,
                            contentColor = Color.White
                        ), // Removed extra comma and parenthesis
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 6.dp
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.titleSmall
                            )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(innerPadding)
                .padding(horizontal = Dimensions.OuterPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kucing),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(10.dp, Color.White, CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = 50.dp, y = 60.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(color = Primary)
                            .padding(4.dp)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile Picture",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            item {
                ItineraryTextfield(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Name",
                    label = "Name",
                    singleLine = true,
                )
            }
            item {
                Column() {
                    Text(
                        text = "Religion",
                        style = MaterialTheme.typography.titleMedium,
                        color = Primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedReligion,
                        onExpandedChange = { expandedReligion = !expandedReligion }
                    ) {
                        OutlinedTextField(
                            value = religion,
                            onValueChange = {},
                            placeholder = { Text("Religion") },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedReligion)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
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
                            expanded = expandedReligion,
                            onDismissRequest = { expandedReligion = false }
                        ) {
                            religionOptions.forEach { selection ->
                                DropdownMenuItem(
                                    text = { Text(selection) },
                                    onClick = {
                                        religion = selection
                                        expandedReligion = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            item {
                ItineraryTextfield(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Masukan email",
                    label = "Email",
                    singleLine = true,
                )
            }
            item {
                ItineraryTextfield(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    placeholder = "Date",
                    label = "Date Of Birth",
                    singleLine = true,
                    modifier = Modifier.width(500.dp),
                    trailingIcon = {
                        Button(
                            onClick = { },
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
            }
            item {
                Column() {
                    Text(
                        text = "Religion",
                        style = MaterialTheme.typography.titleMedium,
                        color = Primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedGender,
                        onExpandedChange = { expandedGender = !expandedGender }

                    ) {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = {},
                            shape = RoundedCornerShape(5.dp),
                            readOnly = true,
                            placeholder = { Text("Gender") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
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
                            expanded = expandedGender,
                            onDismissRequest = { expandedGender = false }
                        ) {
                            genderOptions.forEach { selection ->
                                DropdownMenuItem(
                                    text = { Text(selection) },
                                    onClick = {
                                        gender = selection
                                        expandedGender = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            item {
                ItineraryTextfield(
                    value = contactNumber,
                    onValueChange = { contactNumber = it },
                    placeholder = "Masukan No Telp",
                    label = "Phone Number",
                    singleLine = true,
                )
            }
        }
    }
}




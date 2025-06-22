package com.example.luminara.ui.screens.trip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.components.DatePickerField
import com.example.luminara.utils.Dimensions
import com.google.firebase.Timestamp

@Composable
fun FormTrip(
    name: String,
    onNameChange: (String) -> Unit,
    description:String,
    onDescriptionChange:(String) -> Unit,
    startDate: Timestamp,
    onStartDateChange:(Timestamp) -> Unit,
    endDate:Timestamp,
    onEndDateChange:(Timestamp) -> Unit,
    imageUri: String
) {
    NameSection(name = name, onNameChange = onNameChange)
    DescriptionSection(description = description, onDescriptionChange = onDescriptionChange)
    DateSection(startDate = startDate, onStartDateChange = onStartDateChange, endDate = endDate, onEndDateChange = onEndDateChange)
}

@Composable
private fun NameSection(name: String, onNameChange: (String) -> Unit) {
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
                text = "Name",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
        }
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = name,
            onValueChange = onNameChange,
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
private fun DescriptionSection(description: String, onDescriptionChange: (String) -> Unit) {
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
                text = "Description",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.Black
            )
        }
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = description,
            onValueChange = onDescriptionChange,
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
private fun DateSection(startDate: Timestamp, onStartDateChange: (Timestamp) -> Unit, endDate: Timestamp, onEndDateChange: (Timestamp) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        DatePickerField(
            label = "Start Date",
            date = startDate,
            onDateChange = onStartDateChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField(
            label = "End Date",
            date = endDate,
            onDateChange = onEndDateChange
        )
    }
}
package com.example.luminara.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DatePickerField(
    label: String,
    date: Long?, // store date as timestamp milliseconds or null
    onDateChange: (Long) -> Unit
) {
    val context = LocalContext.current
    val formattedDate = remember(date) {
        if (date != null) {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(date))
        } else {
            ""
        }
    }

    var showDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = formattedDate,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true, // prevent keyboard input
        trailingIcon = {
            IconButton(onClick = { showDialog = true }) {
                Icon(Icons.Outlined.DateRange, contentDescription = "Pick date")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (showDialog) {
        // Initialize calendar with current date or today
        val calendar = Calendar.getInstance().apply {
            if (date != null) timeInMillis = date
        }
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                onDateChange(calendar.timeInMillis)
                showDialog = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}
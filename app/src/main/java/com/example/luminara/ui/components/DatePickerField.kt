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
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun DatePickerField(
    label: String,
    date: String, // store date as timestamp milliseconds or null
    onDateChange: (String) -> Unit
) {
    val context = LocalContext.current
    val displayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    isoFormat.timeZone = TimeZone.getTimeZone("UTC")

    var showDialog by remember { mutableStateOf(false) }

    val initialCalendar = remember(date) {
        Calendar.getInstance().apply {
            if (date.isNotEmpty()) {
                try {
                    val parsed = isoFormat.parse(date)
                        ?: displayFormat.parse(date)
                    if (parsed != null) {
                        time = parsed
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    fun parseDateForDisplay(dateString: String): String {
        return try {
            if (dateString.isBlank()) return ""
            val isoFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            val displayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val parsedDate = isoFormat.parse(dateString)
                ?: displayFormat.parse(dateString)
            parsedDate?.let { displayFormat.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    val formattedDate = parseDateForDisplay(date)

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
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val newCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                    set(year, month, dayOfMonth, 0, 0, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val isoDateString = isoFormat.format(newCalendar.time)
                onDateChange(isoDateString)
                showDialog = false
            },
            initialCalendar.get(Calendar.YEAR),
            initialCalendar.get(Calendar.MONTH),
            initialCalendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            // Optional: Set date constraints
            // datePicker.minDate = ...
            // datePicker.maxDate = ...
        }.show()
    }
}
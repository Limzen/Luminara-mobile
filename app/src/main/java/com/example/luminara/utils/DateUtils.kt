package com.example.luminara.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(dateString: String): String {
        return try {
            OffsetDateTime.parse(dateString)
                .format(DateTimeFormatter.ofPattern("MMM dd"))
        } catch (e: DateTimeParseException) {
            "N/A" // Fallback for invalid dates
        }
    }
}
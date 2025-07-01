package com.example.luminara.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

object DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatNormalDate(dateString: String): String {
        return try {
            OffsetDateTime.parse(dateString)
                .format(DateTimeFormatter.ofPattern("MMM dd"))
        } catch (e: DateTimeParseException) {
            "N/A" // Fallback for invalid dates
        }
    }

    fun formatDate(dateString: String): String {
        return try {
            val dateTime = OffsetDateTime.parse(dateString)
            val now = OffsetDateTime.now()
            val secondsDiff = ChronoUnit.SECONDS.between(dateTime, now)
            when {
                secondsDiff < 60 -> {
                    "$secondsDiff second${if (secondsDiff != 1L) "s" else ""} ago"
                }
                secondsDiff < 3600 -> {
                    val minutesDiff = ChronoUnit.MINUTES.between(dateTime, now)
                    "$minutesDiff minute${if (minutesDiff != 1L) "s" else ""} ago"
                }
                secondsDiff < 86400 -> {
                    val hoursDiff = ChronoUnit.HOURS.between(dateTime, now)
                    "$hoursDiff hour${if (hoursDiff != 1L) "s" else ""} ago"
                }
                secondsDiff < 432000 -> { // 5 days in seconds
                    val daysDiff = ChronoUnit.DAYS.between(dateTime, now)
                    "$daysDiff day${if (daysDiff != 1L) "s" else ""} ago"
                }
                else -> {
                    dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                }
            }
        } catch (e: DateTimeParseException) {
            "N/A" // Fallback for invalid dates
        }
    }



}
package com.example.inkspire.domain.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CommonUtils() {
    companion object {

        const val CREATE_NOTE = "create_note"

        fun formatTimestamp(timestamp: Long): String {
            val calendar = Calendar.getInstance()

            val inputCalendar = Calendar.getInstance().apply { timeInMillis = timestamp }

            val isToday = inputCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    inputCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)

            val isYesterday = inputCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    inputCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) - 1

            return when {
                isToday -> SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(timestamp))
                isYesterday -> "Yesterday " + SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(timestamp))
                inputCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) -> SimpleDateFormat("MMMM d", Locale.ENGLISH).format(Date(timestamp))
                else -> SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(Date(timestamp))
            }
        }
    }
}
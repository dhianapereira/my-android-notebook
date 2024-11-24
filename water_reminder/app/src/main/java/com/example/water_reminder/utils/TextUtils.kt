package com.example.water_reminder.utils

import java.util.Calendar

object TextUtils {
    fun getGreetings(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
    }
}
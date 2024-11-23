package com.example.habits.core

import com.example.habits.collections.HabitItem

interface HabitsRepository {
    fun fetch() : List<HabitItem>

    fun addRandomHabit()

    fun toggleCompleted(id: String)
}
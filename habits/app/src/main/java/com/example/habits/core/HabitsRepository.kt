package com.example.habits.core

import com.example.habits.collections.HabitItem

interface HabitsRepository {
    fun fetch() : List<HabitItem>

    fun addHabit(name: String, habitDaysSelected: List<Int>)

    fun toggleCompleted(id: String)
}
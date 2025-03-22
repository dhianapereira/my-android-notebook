package com.example.habits.dummy

import com.example.habits.collections.HabitItem
import com.example.habits.core.HabitsRepository
import java.util.*

/**
 * Mock data with [HabitItem] for the collection.
 */
object MockHabits : HabitsRepository{
    private val habitItemList: MutableList<HabitItem> = mutableListOf()

    override fun fetch() = habitItemList.map { it.copy() }

    override fun addHabit(name: String, habitDaysSelected: List<Int>) {
        habitItemList.add(
            HabitItem(id = UUID.randomUUID().toString(), title = name, isCompleted = false)
        )
    }

    override fun toggleCompleted(id: String) {
        val habitIndex = findHabitIndexById(id)
        val habit = habitItemList[habitIndex]
        habitItemList[habitIndex] = habit.copy(isCompleted = !habit.isCompleted)
    }

    private fun findHabitIndexById(id: String) = habitItemList.indexOfFirst { habitItem ->
        habitItem.id == id
    }
}
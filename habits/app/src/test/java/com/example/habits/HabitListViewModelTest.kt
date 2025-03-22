package com.example.habits

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.habits.collections.HabitItem
import com.example.habits.collections.HabitListViewModel
import com.example.habits.core.HabitsRepository
import com.example.habits.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class HabitListViewModelTest {
    private lateinit var repository: HabitsRepository
    private lateinit var viewModel: HabitListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock(HabitsRepository::class.java)
        viewModel = HabitListViewModel(repository)
    }

    @Test
    fun `Should have empty habit list when no habit is added`() {
        `when`(repository.fetch()).thenReturn(emptyList())
        val state = viewModel.state().getOrAwaitValue()
        assert(state.list.isEmpty())
    }

    @Test
    fun `Should have not empty habit list when at least one habit is added`() {
        val list = listOf(
            HabitItem(
                id = "ID",
                title = "Test Habit",
                isCompleted = false
            )
        )
        `when`(repository.fetch()).thenReturn(list)
        val state = viewModel.state().getOrAwaitValue()
        assert(state.list.isNotEmpty())
    }

    @Test
    fun `Should add a new habit when addHabit is called`() {
        val name = "Test Habit"
        val habitDaysSelected = listOf(1, 2)
        val list = listOf(
            HabitItem(
                id = "ID",
                title = name,
                isCompleted = false
            )
        )
        `when`(repository.fetch()).thenReturn(emptyList())
        `when`(repository.addHabit(name, habitDaysSelected)).then {
            `when`(repository.fetch()).thenReturn(list)
        }
        viewModel.addHabit(name, habitDaysSelected)
        val state = viewModel.state().getOrAwaitValue()
        assert(state.list.isNotEmpty())
    }
}
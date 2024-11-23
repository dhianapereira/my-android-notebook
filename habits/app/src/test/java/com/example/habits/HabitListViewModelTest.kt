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
    fun `Should add a new habit when addRandomHabit is called`() {
        val list = listOf(
            HabitItem(
                id = "ID",
                title = "Test Habit",
                isCompleted = false
            )
        )
        `when`(repository.fetch()).thenReturn(emptyList())
        `when`(repository.addRandomHabit()).then {
            `when`(repository.fetch()).thenReturn(list)
        }
        viewModel.addRandomHabit()
        val state = viewModel.state().getOrAwaitValue()
        assert(state.list.isNotEmpty())
    }
}
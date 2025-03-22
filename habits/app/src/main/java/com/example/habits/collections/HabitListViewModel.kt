package com.example.habits.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habits.core.HabitsRepository

class HabitListViewModel(private val repository: HabitsRepository) : ViewModel() {
    private val uiState: MutableLiveData<HabitListUiState> by lazy {
        MutableLiveData<HabitListUiState>(HabitListUiState(list = repository.fetch()))
    }

    fun state(): LiveData<HabitListUiState> = uiState

    fun addHabit(name: String, habitDaysSelected: List<Int>) {
        repository.addHabit(name, habitDaysSelected)
        refresh()
    }

    fun toggleCompleted(id: String) {
        repository.toggleCompleted(id)
        refresh()
    }

    private fun refresh() {
        uiState.value?.let {
            uiState.value = it.copy(
                list = repository.fetch().sortedBy { item -> item.isCompleted }
            )
        }
    }

    data class HabitListUiState(val list: List<HabitItem>)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: HabitsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HabitListViewModel(repository) as T
        }
    }
}
package com.example.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.entities.Pokemon
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonListViewModel(private val getAllPokemons: GetAllPokemonsUseCase) : ViewModel() {
    private val _uiState: MutableLiveData<PokemonListUiState> = MutableLiveData()
    fun state(): LiveData<PokemonListUiState> = _uiState

    init {
        fetchPokemons()
    }

    fun fetchPokemons() {
        viewModelScope.launch {
            _uiState.value = PokemonListUiState.Loading
            try {
                val pokemons = withContext(Dispatchers.IO) {
                    getAllPokemons.call()
                }
                _uiState.value = PokemonListUiState.Success(pokemons)
            } catch (e: Exception) {
                _uiState.value = PokemonListUiState.Error(e)
            }
        }
    }

    sealed class PokemonListUiState {
        data object Loading : PokemonListUiState()
        data class Success(val list: List<Pokemon>) : PokemonListUiState()
        data class Error(val error: Exception) : PokemonListUiState()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val getAllPokemons: GetAllPokemonsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PokemonListViewModel(getAllPokemons) as T
        }
    }
}

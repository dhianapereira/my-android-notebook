package com.example.pokedex.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.entities.Pokemon
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase

class MainActivity : ComponentActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: PokemonListViewModel by viewModels {
        PokemonListViewModel.Factory(GetAllPokemonsUseCase())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.state().observe(this) { state ->
            when (state) {
                is PokemonListViewModel.PokemonListUiState.Loading -> {
                    showLoadingState()
                }

                is PokemonListViewModel.PokemonListUiState.Success -> {
                    showSuccessState(state.list)
                }

                is PokemonListViewModel.PokemonListUiState.Error -> {
                    showErrorState()
                }
            }
        }
    }

    private fun showLoadingState() {
        binding.loading.visibility = View.VISIBLE
        binding.errorContainer.visibility = View.GONE
        binding.pokemonRecycleView.visibility = View.GONE
    }

    private fun showSuccessState(list: List<Pokemon>) {
        binding.loading.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
        binding.pokemonRecycleView.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(this)
        binding.pokemonRecycleView.layoutManager = layoutManager
        binding.pokemonRecycleView.adapter = PokemonAdapter(list)
    }

    private fun showErrorState() {
        binding.loading.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
        binding.pokemonRecycleView.visibility = View.GONE

        binding.tryAgainButton.setOnClickListener {
            viewModel.fetchPokemons()
        }
    }
}

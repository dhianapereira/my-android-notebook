package com.example.pokedex.domain.use_cases

import com.example.pokedex.data.Endpoints
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.domain.entities.Pokemon

class GetAllPokemonsUseCase {
    fun call(limit: Int = 100): List<Pokemon> {
        val response = PokemonRepository.getAll(limit)
        val pokemons: List<Pokemon> = response?.let {
            it.results.mapNotNull { result ->
                val number = result.url.replace(
                    Endpoints.BASE_URL + Endpoints.POKEMON,
                    ""
                ).replace("/", "").toInt()
                val pokemonApiResponse = PokemonRepository.getByNumber(number)
                pokemonApiResponse?.let { pokemon ->
                    Pokemon(
                        pokemon.id,
                        pokemon.name,
                        pokemon.types.map { pokemonTypeSlot ->
                            pokemonTypeSlot.type
                        }
                    )
                }
            }
        } ?: emptyList()

        return pokemons
    }
}
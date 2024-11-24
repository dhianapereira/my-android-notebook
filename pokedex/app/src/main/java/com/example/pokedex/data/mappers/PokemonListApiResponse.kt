package com.example.pokedex.data.mappers

data class PokemonListApiResponse(
    val results: List<PokemonResponse>,
    val count: Int,
    val previous: String?,
    val next: String?,
)

data class PokemonResponse(val name: String, val url: String)

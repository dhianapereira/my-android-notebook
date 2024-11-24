package com.example.pokedex.data.mappers

import com.example.pokedex.domain.entities.PokemonType

data class PokemonApiResponse(val id: Int, val name: String, val types: List<PokemonTypeSlot>)

data class PokemonTypeSlot(val slot: String, val type: PokemonType)
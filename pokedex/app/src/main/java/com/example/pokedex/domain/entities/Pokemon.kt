package com.example.pokedex.domain.entities

data class Pokemon(
    val number: Number,
    val name: String,
    val types: List<PokemonType>,
) {
    fun formattedNumber(): String {
        return "Nº ${number.toString().padStart(3, '0')}"
    }

    fun getImage(): String {
        val formattedNumber = number.toString().padStart(3, '0')
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/${formattedNumber}.png"
    }
}
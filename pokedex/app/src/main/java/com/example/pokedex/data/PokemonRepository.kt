package com.example.pokedex.data

import com.example.pokedex.domain.interfaces.PokemonService
import com.example.pokedex.data.mappers.PokemonApiResponse
import com.example.pokedex.data.mappers.PokemonListApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun getAll(limit: Int): PokemonListApiResponse? {
        val call = service.getAll(limit)
        return call.execute().body()
    }

    fun getByNumber(number: Int): PokemonApiResponse? {
        val call = service.getByNumber(number)
        return call.execute().body()
    }
}

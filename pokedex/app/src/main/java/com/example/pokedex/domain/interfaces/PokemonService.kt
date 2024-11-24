package com.example.pokedex.domain.interfaces

import com.example.pokedex.data.Endpoints
import com.example.pokedex.data.mappers.PokemonApiResponse
import com.example.pokedex.data.mappers.PokemonListApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(Endpoints.POKEMON)
    fun getAll(@Query("limit") limit: Int): Call<PokemonListApiResponse>

    @GET("${Endpoints.POKEMON}/{number}")
    fun getByNumber(@Path("number") number: Int): Call<PokemonApiResponse>
}
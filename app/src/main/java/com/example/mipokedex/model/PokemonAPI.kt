package com.example.mipokedex.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonAPI {
    @GET
    fun getPokemon(
        @Url url : String?
    ): Call<Pokemon>
}
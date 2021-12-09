package com.example.mipokedex.model

import com.google.gson.annotations.SerializedName

class Pokedetalle {
    @SerializedName("base_experience")
    var experiencia: Int? = null

    @SerializedName("height")
    var tamano: Int? = null

    @SerializedName("weight")
    var peso: Int? = null

    @SerializedName("id")
    var numero: Int? = null

    @SerializedName("types")
    var tipos: List<PokeTipos>?  = null

    @SerializedName("sprites")
    var imagen: PokeImagen?  = null

}

class PokeTipos{
    @SerializedName("type")
    var tipo: Poketipo? = null
}

class Poketipo {
    @SerializedName("name")
    var nombreTipo: String? = null
}

class PokeImagen{
    @SerializedName("front_default")
    var frente: String? = null
}
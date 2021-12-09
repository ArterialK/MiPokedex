package com.example.mipokedex.model

import com.google.gson.annotations.SerializedName
import com.example.mipokedex.model.Pokenombre

class Pokemon {
    @SerializedName("results")
    var pokeLista: List<Pokenombre>? = null
}

class Pokenombre {
    @SerializedName("name")
    var nombre: String? = null

    @SerializedName("url")
    var liga: String? = null
}
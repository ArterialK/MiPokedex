package com.example.mipokedex.model

import com.google.gson.annotations.SerializedName

class Pokenombre {
    @SerializedName("name")
    var nombre: String? = null

    @SerializedName("url")
    var liga: String? = null
}
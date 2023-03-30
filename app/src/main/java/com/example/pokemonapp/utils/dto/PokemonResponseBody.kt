package com.example.pokemonapp.utils.dto

import com.google.gson.annotations.SerializedName

data class PokemonResponseBody (
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("results")
    val results: List<PokemonModel>
)
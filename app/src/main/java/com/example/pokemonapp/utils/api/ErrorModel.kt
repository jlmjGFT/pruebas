package com.example.pokemonapp.utils.api

class ErrorModel(var type: Type, val message: String, val detailType: String? = null) {

    constructor(type: Type, message: String) : this(type, message, null)

    enum class Type {
        FATAL,
        TOLERABLE
    }
}
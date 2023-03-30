package com.example.pokemonapp.utils.api

import java.io.IOException

class FrontException(val code: Int?, override val message: String?, override val cause: Throwable?) :
    IOException(message, cause)

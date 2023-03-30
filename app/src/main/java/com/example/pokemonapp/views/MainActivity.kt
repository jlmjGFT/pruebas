package com.example.pokemonapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.R
import com.example.pokemonapp.viewmodel.PokemonViewModel

/**
 * Clase Main principal contenedor del aplicativo
 */
class MainActivity : AppCompatActivity() {

    /**
     * Variable global que contiene el viewmodel
     */
    private lateinit var viewModel: PokemonViewModel

    /**
     * Metodo onCreate inicia la instancia de la vista y del xml (R.layout.activity_main)
     * @param savedInstanceState obtiene el estado de la instancia invocada (en este caso MainActivity)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //se enlaza la vista con la clase
        setContentView(R.layout.activity_main)

        //instancia para generar el viewmodel
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }
}
package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Clase Main principal contenedor del aplicativo
 */
class MainActivity : AppCompatActivity() {

    /**
     * Metodo onCreate inicia la instancia de la vista y del xml (R.layout.activity_main)
     * @param savedInstanceState obtiene el estado de la instancia invocada (en este caso MainActivity)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
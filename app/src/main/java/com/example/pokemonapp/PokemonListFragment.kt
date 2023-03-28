package com.example.pokemonapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * Clase para enlistar los pokemones obtenidos
 */
class PokemonListFragment : Fragment() {

    /**
     * clase "constructora" del fragmento (normalmente utilizada para inicializar viewmodels)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Metodo para inflar la vista y enlazar el xml con la clase
     * @param inflater obtiene el inflador de vistas de la clase
     * @param container obtiene el grupo de la vista a la que pertence el fragmento
     * @param savedInstanceState obtiene el conjunto de parametros guardados de la instancia generada
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //se enlaza y se infla la vista con la clase
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    /**
     * Metodo para validar la vista creada (aqui se implementa el inicio de la logica y carga de datos de interfaz de usuario)
     * @param view obtiene la vista generada
     * @param savedInstanceState obtiene el conjunto de parametros guardados de la instancia generada
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addActions()
    }

    /**
     * Metodo para instanciar los llamados de la vista de los objetos que se muestran en el xml
     */
    fun addActions(){
        // boton siguiente al hacer onClick
        requireView().findViewById<Button>(R.id.btn_siguiente).setOnClickListener {
            // agrega el fragmento de detalle del pokemon a la pila y enlaza la navegacion a la siguiente pantalla
            findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment)
        }

    }
}
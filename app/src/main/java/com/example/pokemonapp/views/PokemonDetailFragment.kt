package com.example.pokemonapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*

/**
 * Clase para obtener el detalle de los pokemones
 */
class PokemonDetailFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
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
    private fun addActions() {

        // boton regresar al hacer onClick
        btn_regresar.setOnClickListener {
            // quita el fragmento actual de la pila
            findNavController().popBackStack()
        }
    }

}
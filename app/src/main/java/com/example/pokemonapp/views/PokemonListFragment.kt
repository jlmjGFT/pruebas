package com.example.pokemonapp.views

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.utils.api.Resource
import com.example.pokemonapp.utils.api.Status
import com.example.pokemonapp.utils.dto.PokemonModel
import com.example.pokemonapp.viewmodel.PokemonViewModel
import com.example.pokemonapp.views.adapter.PokemonListAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

/**
 * Clase para enlistar los pokemones obtenidos
 */
class PokemonListFragment : Fragment() {


    private lateinit var viewmodel : PokemonViewModel

    private val pokemonListObserver =  Observer<Resource<List<PokemonModel>>>{
        when (it.status){
            Status.LOADING->{

            }
            Status.SUCCESS->{
                val adapter = PokemonListAdapter(it.data!!)
                rv_pokemon_list.adapter = adapter
            }
            Status.ERROR->{
            }
        }
    }

    /**
     * clase "constructora" del fragmento (normalmente utilizada para inicializar viewmodels)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
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
        createObservers()
        viewmodel.getPokemons()
        rv_pokemon_list.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun createObservers() {
        viewmodel.pokemonList.observe(viewLifecycleOwner,pokemonListObserver)
    }
}
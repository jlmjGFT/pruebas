package com.example.pokemonapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.repository.PokemonRepository
import com.example.pokemonapp.utils.api.Resource
import com.example.pokemonapp.utils.dto.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Clase de tipo ViewModel donde estará la logica del proyecto
 */
class PokemonViewModel : ViewModel(){

    /**
     * instancia de la invocación del repositorio
     */
    private val pokemonRepository = PokemonRepository()

    /**
     * Live data que escucha los cambios del llamado del micro servicio para obtener los pokemones
     */
    val pokemonList = MutableLiveData<Resource<List<PokemonModel>>>()

    /**
     * Método utilizado para obtencion de pokemones
     */
    fun getPokemons(){

        // Se le avisa al escucha que inicialice un dialogo de carga
        pokemonList.value =  Resource.loading(null)

        // corrutina que manda a hilo secundario la solicitud para obtener pokemones para procesarla... a este proceso se le conoce como proceso ascincrono.
        viewModelScope.launch {

            // del repositorio se solicitan los pokemones
            val result = pokemonRepository.getPokemons()

            // aqui se le ordena regresar al hilo primario, una vez que el proceso del repositorio se complete
            withContext(Dispatchers.Main){

                //seteamos la información que nos retorna el repositorio para que el live data pueda notificar a la vista la carga de pokemones o en su defecto un error
                pokemonList.value = result
            }
        }
    }

    fun setSelectedPokemon(position: Int) {
        val pokemon = pokemonList.value!!.data!![position]
        Log.i(this.javaClass.simpleName, "************************************Este es el pokemon:${pokemon.name}")
    }
}
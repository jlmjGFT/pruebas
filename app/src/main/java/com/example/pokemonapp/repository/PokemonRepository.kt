package com.example.pokemonapp.repository

import com.example.pokemonapp.App
import com.example.pokemonapp.BuildConfig
import com.example.pokemonapp.R
import com.example.pokemonapp.utils.api.*
import com.example.pokemonapp.utils.dto.PokemonModel
import java.lang.Exception

/**
 * Clase repositorio que contiene los llamados a las bases de datos o invocacion a microservicios
 */
class PokemonRepository {

    /**
     * atributo privado que permite la conexión del aplicativo con el microservicio, al inicializar exige una url base que es seteada desde las variables globales del Gradle
     */
    private var service: ApiService = RequestManager(BuildConfig.BASE_URL).create(ApiService::class.java)

    /**
     * Instancia de la applicación para obtener recursos del aplicativo
     */
    val application = App.instance

    /**
     * Metodo implementado para la obtencion de pokemones
     */
    suspend fun getPokemons(): Resource<List<PokemonModel>> {

        // Obtenemos la respuesta del llamado del microservicio o cachamos el error generado
        return try {

            // aqui es donde se realiza el llamado al microservicio!!, obtenemos del aplicativo el recurso("R.string.service_pokemon") para agregarlo a la cadena de la url base
            val response = service.getPokemonList(application.getString(R.string.service_pokemon))

            // retornamos el llamado del microservicio
            Resource(Status.SUCCESS, response.body()!!.results, null)
        } catch (e: Exception) {

            //cachamos la excepcion y retonamos un error al viewmodel para ser mostrado en la vista
            Resource(Status.ERROR, null, ErrorModel(ErrorModel.Type.TOLERABLE, "No se pudo consultar el servicio, intente nuevamente"))
        }
    }
}
package com.example.pokemonapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.utils.dto.PokemonModel
import kotlinx.android.synthetic.main.item_pokemon_list.view.*

class PokemonListAdapter(val pokemons : List<PokemonModel>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list,parent,false))
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        holder.setViewItem(position, pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun setViewItem(position: Int, pokemonModel: PokemonModel) {
            view.tv_item_pokemon_list_number.text = "$position.-"
            view.tv_item_pokemon_list_name.text = pokemonModel.name
        }
    }
}
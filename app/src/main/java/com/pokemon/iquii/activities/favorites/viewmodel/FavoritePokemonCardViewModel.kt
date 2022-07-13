package com.pokemon.iquii.activities.favorites.viewmodel

import android.content.Context
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.business.models.Pokemon

class FavoritePokemonCardViewModel(private val pokemon: Pokemon, private val context: Context): PokemonCardViewModel(pokemon, context) {



}
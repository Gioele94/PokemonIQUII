package com.pokemon.iquii.activities.pokemons.viewmodel

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.database.repository.pokemon.PokemonFavoriteRepository

open class PokemonCardViewModel(private val pokemon: Pokemon, private val context: Context) :
    BaseObservable() {

    interface Listener {
        fun onPokemonClicked(view: View, pokemon: Pokemon)
        fun onSetOrRemoveFavorite(view: View, pokemon: Pokemon, isAlreadyFavorite: Boolean, viewModelCard: PokemonCardViewModel)
    }

    var listener: Listener? = null

    @Bindable
    fun getNamePokemon(): SpannableStringBuilder? {
        return SpannableStringBuilder(pokemon.name)
    }

    @Bindable
    fun getIdPokemon(): String {
        return "#" + pokemon.id

    }

    open var pokemonClickListener: View.OnClickListener = View.OnClickListener {
        listener?.onPokemonClicked(it, pokemon)
    }

    var pokemonFavoriteClickListener: View.OnClickListener = View.OnClickListener {
        listener?.onSetOrRemoveFavorite(it, pokemon, getIsFavorite(), this)
    }


    @Bindable
    fun getImageUrl(): String? {
        return pokemon.sprites.frontDefault
    }

    @Bindable
    fun getIsFavorite(): Boolean {
        return PokemonFavoriteRepository().getPokemon(pokemon.id) != null
    }

}
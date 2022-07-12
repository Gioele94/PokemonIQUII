package com.pokemon.iquii.activities.pokemons.viewmodel

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.pokemon.iquii.business.models.Pokemon

class PokemonCardViewModel(private val pokemon: Pokemon, private val context: Context) :
    BaseObservable() {

    interface Listener {
        fun onPokemonClicked(view: View, pokemon: Pokemon)
    }

    var listener: Listener? = null

    @Bindable
    fun getMessage(): SpannableStringBuilder? {
        return SpannableStringBuilder(pokemon.name)

    }

    var pokemonClickListener: View.OnClickListener = View.OnClickListener {
        listener?.onPokemonClicked(it, pokemon)
    }


    @Bindable
    fun getImageUrl(): String? {
        return pokemon.sprites.frontDefault
    }

}
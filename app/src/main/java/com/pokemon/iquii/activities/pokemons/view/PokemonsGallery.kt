package com.pokemon.iquii.activities.pokemons.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pokemon.iquii.activities.pokemons.actionlistener.PokemonsListActionListener
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonsListViewModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.fragment.PokemonIquiiListFragment

class PokemonsGallery : PokemonIquiiListFragment<PokemonsGallery>(), PokemonsListActionListener {

    override fun forceCloseMenuSwupe(view: View) {}

    override val viewModel by lazy { PokemonsListViewModel(this, this) }

    override val showLineDivide: Boolean = true

    override fun onReloadCLicked() {}

    override fun onPokemonClicked(view: View, pokemon: Pokemon) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    companion object {

        fun newInstance(): PokemonsGallery {
            val bundle = Bundle()
            val fragment = PokemonsGallery()
            fragment.arguments = bundle
            return fragment
        }
    }

}
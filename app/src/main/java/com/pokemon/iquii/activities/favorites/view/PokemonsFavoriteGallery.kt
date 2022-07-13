package com.pokemon.iquii.activities.favorites.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pokemon.iquii.activities.favorites.viewmodel.FavoritePokemonViewModel
import com.pokemon.iquii.activities.pokemons.actionlistener.PokemonsListActionListener
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.fragment.PokemonIquiiListFragment

class PokemonsFavoriteGallery : PokemonIquiiListFragment<PokemonsFavoriteGallery>(),
    PokemonsListActionListener {

    override val viewModel by lazy { FavoritePokemonViewModel(this, this) }

    override val showLineDivide: Boolean = true

    override val removeItemDecoration: Boolean = true

    override fun onReloadCLicked() {}

    override fun onPokemonClicked(view: View, pokemon: Pokemon) {

    }

    override fun onSetOrRemoveFavorite(
        view: View,
        pokemon: Pokemon,
        isAlreadyFavorite: Boolean,
        viewModelCard: PokemonCardViewModel
    ) {
        viewModel.adapter?.removeItemByItem(pokemon)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadItem(true, true, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    companion object {

        fun newInstance(): PokemonsFavoriteGallery {
            val bundle = Bundle()
            val fragment = PokemonsFavoriteGallery()
            fragment.arguments = bundle
            return fragment
        }
    }

}
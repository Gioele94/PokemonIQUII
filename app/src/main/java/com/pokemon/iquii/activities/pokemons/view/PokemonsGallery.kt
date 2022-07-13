package com.pokemon.iquii.activities.pokemons.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pokemon.iquii.R
import com.pokemon.iquii.activities.pokemonDetails.view.PokemonDetailsDialog
import com.pokemon.iquii.activities.pokemons.actionlistener.PokemonsListActionListener
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonsListViewModel
import com.pokemon.iquii.business.mapperBusinessToDB.convertPokemonModelToDB
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.fragment.PokemonIquiiListFragment
import com.pokemon.iquii.database.repository.pokemon.PokemonFavoriteRepository


class PokemonsGallery : PokemonIquiiListFragment<PokemonsGallery>(), PokemonsListActionListener {

    override val viewModel by lazy { PokemonsListViewModel(this, this) }

    override val showLineDivide: Boolean = true

    override val removeItemDecoration: Boolean = true

    override fun onReloadCLicked() {}

    override fun onPokemonClicked(view: View, pokemon: Pokemon) {
        showBottomSheetDialog(pokemon)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onSetOrRemoveFavorite(
        view: View,
        pokemon: Pokemon,
        isAlreadyFavorite: Boolean,
        viewModelCard: PokemonCardViewModel
    ) {
        if (isAlreadyFavorite) {
            PokemonFavoriteRepository().delete(convertPokemonModelToDB(pokemon, true))
        } else {
            PokemonFavoriteRepository().insert(convertPokemonModelToDB(pokemon, true))
        }
        viewModelCard.notifyChange()
    }

    private fun showBottomSheetDialog(pokemon: Pokemon) {
        val bottomSheetDialog = activity?.let { PokemonDetailsDialog.newInstance(pokemon.id) }
        bottomSheetDialog?.show(childFragmentManager, PokemonDetailsDialog::class.java.name)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        viewModel.adapter?.notifyDataSetChanged()
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
package com.pokemon.iquii.activities.pokemonDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pokemon.iquii.R
import com.pokemon.iquii.activities.pokemonDetails.viewmodel.PokemonDetailsViewModel
import com.pokemon.iquii.business.mapperDbToBusiness.convertPokemonDBToModel
import com.pokemon.iquii.database.repository.pokemon.PokemonRepository
import com.pokemon.iquii.databinding.PokemonDetailDialogBinding


class PokemonDetailsDialog : BottomSheetDialogFragment() {

    private lateinit var binding: PokemonDetailDialogBinding
    private lateinit var viewModel: PokemonDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailDialogBinding.inflate(inflater, container, false)
        val pokemonId = arguments?.getInt(ITEM_ID, -1) ?: -1
        val pokemon = PokemonRepository().getPokemon(pokemonId)

        if (pokemon == null) {
            dismiss()
            return null
        }

        viewModel =
            activity?.let { PokemonDetailsViewModel(convertPokemonDBToModel(pokemon), it) } ?: run {
                dismiss()
                return null
            }

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {

        const val ITEM_ID: String = "itemId"

        fun newInstance(pokemonId: Int): PokemonDetailsDialog {
            val bundle = Bundle()
            bundle.putInt(ITEM_ID, pokemonId)
            val fragment = PokemonDetailsDialog()
            fragment.arguments = bundle
            return fragment
        }
    }
}

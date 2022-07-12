package com.pokemon.iquii.activities.pokemons.actionlistener

import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon
import com.pokemon.iquii.components.actionlistener.ReloadListener

interface PokemonsListActionListener : PokemonIquiiListenerLoadItemCommon, ReloadListener,
    PokemonCardViewModel.Listener
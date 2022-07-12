package com.pokemon.iquii.components.actionlistener

interface PokemonIquiiListenerLoadItemCommon : PokemonIquiiListListener {
    fun onItemFetched()
    fun showSwipeRefreshLayout()
}

package com.pokemon.iquii.components.viewmodel

import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapter

abstract class CommonListViewModel<T>(actionListener : PokemonIquiiListenerLoadItemCommon) : CommonViewModel<PokemonIquiiListenerLoadItemCommon>(actionListener){

    abstract val adapter : PokemonIquiiCommonAdapter<*>?

    abstract fun isEndReached() : Boolean

    abstract fun loadItem(clearDataSet: Boolean, getItemsFirstTime: Boolean, deleteItemIntoDB: Boolean)

    abstract fun stopLoading()

    abstract fun reset(getItemsFirstTime : Boolean)

}
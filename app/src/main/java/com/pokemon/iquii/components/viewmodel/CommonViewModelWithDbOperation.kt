package com.pokemon.iquii.components.viewmodel

import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon

abstract class CommonViewModelWithDbOperation<T>(actionListener : PokemonIquiiListenerLoadItemCommon) :
        CommonListViewModel<PokemonIquiiListenerLoadItemCommon>(actionListener) {

    abstract fun deleteAllItemIntoDB()
    abstract fun insertAllItemIntoDB(items : List<*>)
}
package com.pokemon.iquii.components.adapter

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil

abstract class PokemonIquiiCommonAdapterWithDbOperation<T>(diffUtilCallBack : DiffUtil.ItemCallback<Any>) : PokemonIquiiCommonAdapter<T>(diffUtilCallBack) {

    abstract fun updateItemIntoDBByItem(item : Any)
    abstract fun replaceDataSetWithItemsDB()
    abstract fun getItemsFromDBLiveData() : LiveData<out List<*>?>?
    abstract fun getItemsFromDB(): List<*>?
    abstract fun addItemsFromDB(items: List<*>, removeLoader: RemoveLoader, clearDataSet: Boolean)
}
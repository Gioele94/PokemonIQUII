package com.pokemon.iquii.components.loadingdatafirsttime.viewmodel

import android.content.Context
import com.pokemon.iquii.R
import com.pokemon.iquii.components.loadingdatafirsttime.LoadingItemsFirstTimeListener
import com.pokemon.iquii.components.viewmodel.CommonViewModel
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer

class LoadDataFirstTime(listener : LoadingItemsFirstTimeListener? = null) : CommonViewModel<LoadingItemsFirstTimeListener>(listener) {

    fun getLabelDescription(context: Context): String?{
        return PokemonIQUIILocalizer.get(R.string.recycleView_loadingData, context)
    }
}
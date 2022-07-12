package com.pokemon.iquii.components.emptylist.viewmodel

import com.pokemon.iquii.R
import com.pokemon.iquii.application.PokemonIQUIIApplication
import com.pokemon.iquii.components.actionlistener.ReloadListener
import com.pokemon.iquii.components.viewmodel.CommonViewModel
import com.pokemon.iquii.components.viewmodel.ViewModelActionListener
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer

class EmptyListViewModel(var listener: ReloadListener? = null) : CommonViewModel<ViewModelActionListener>(listener)  {

    val labelDescription: String?
        get() {
            return PokemonIQUIILocalizer.get(R.string.emptyList, PokemonIQUIIApplication.getStaticInstance().applicationContext)
        }

}
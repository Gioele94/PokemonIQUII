package com.pokemon.iquii.components.actionlistener

import com.pokemon.iquii.components.viewmodel.ViewModelActionListener

interface ReloadListener : ViewModelActionListener {
        fun onReloadCLicked()
}
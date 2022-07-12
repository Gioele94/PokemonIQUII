package com.pokemon.iquii.components.viewmodel

import androidx.lifecycle.LifecycleObserver

abstract class CommonViewModel<AL : ViewModelActionListener>(var actionListener:AL?): LifecycleObserver {

}
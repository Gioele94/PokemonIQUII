package com.pokemon.iquii.components.networkerror.viewmodel

import android.view.View
import androidx.databinding.BaseObservable
import com.pokemon.iquii.components.actionlistener.ReloadListener

class NetworkErrorViewModel : BaseObservable() {

    var listener: ReloadListener? = null

    var reloadListener: View.OnClickListener = View.OnClickListener { listener?.onReloadCLicked() }
}
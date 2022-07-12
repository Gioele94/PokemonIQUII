package com.pokemon.iquii.components.actionlistener

import android.view.View
import androidx.annotation.StringRes
import com.pokemon.iquii.components.viewmodel.ViewModelActionListener

interface PokemonIquiiGenericFragmentListener : ViewModelActionListener {
    fun showProgressDialog()
    fun hideProgressDialog()
    fun onInternetNetworkError()
    fun showDialogYesNo(title : Int, message: Int, okListener : View.OnClickListener, closeListener : View.OnClickListener? = null, vararg params:String?)
    fun showDialogOk(@StringRes title: Int, @StringRes message: Int?, messageString: String?, callback : (() -> Unit)? = null)
}
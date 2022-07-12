package com.pokemon.iquii.components.progressdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.pokemon.iquii.R


class ProgressDialog : DialogFragment {

    private var showing = false
    private var currentActivity: AppCompatActivity? = null


    constructor(currentActivity: AppCompatActivity) : super() {
        this.currentActivity = currentActivity
    }

    internal fun hideDialog() {
        if (showing) {
            dismiss()
        }
    }

    internal fun showDialog() {
        if (!showing && currentActivity != null) {
            show(currentActivity!!.supportFragmentManager, ProgressDialog::class.java.name)
            showing = true
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.circular_progress_bar, container, false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        retainInstance = true
        setStyle(STYLE_NORMAL, R.style.LoadingScreenTheme)
    }


}
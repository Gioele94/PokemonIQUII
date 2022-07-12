package com.pokemon.iquii.components.fragment

import android.net.Uri
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.pokemon.iquii.R
import com.pokemon.iquii.components.actionlistener.PokemonIquiiGenericFragmentListener
import com.pokemon.iquii.components.activity.PokemonIquiiActivity
import com.pokemon.iquii.components.settings.Settings
import io.reactivex.disposables.CompositeDisposable

abstract class PokemonIquiiGenericFragment : DialogFragment(), PokemonIquiiGenericFragmentListener,
    LifecycleOwner {

    var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    var imageUri: Uri? = null
    var compositeDisposable = CompositeDisposable()

    val settings: Settings
        get() = (activity as PokemonIquiiActivity).settings!!

    override fun onStart() {
        super.onStart()

        if (useFragmentTitle()) {
            setTitle()
        }
    }

    fun setTitle() {
        (activity as PokemonIquiiActivity?)?.setCustomTitleOnActivity((activity as PokemonIquiiActivity).activityTitle)
    }

    fun pushFragment(fragment: Fragment, containerResId: Int) {
        (activity as PokemonIquiiActivity?)?.pushFragment(fragment, containerResId)
    }

    private fun popLastFragment() {
        (activity as PokemonIquiiActivity?)?.popLastFragment()
    }

    fun hideKeyboard() {
        (activity as PokemonIquiiActivity?)?.hideKeyboard()
    }

    private fun useFragmentTitle(): Boolean {
        return true
    }

    /**
     * @return true if the event has been handled, false otherwise
     */
    protected fun onSupportNavigateUp(): Boolean {
        return false
    }

    fun runOnUiThread(runnable: Runnable) {
        val activity = activity ?: return
        activity.runOnUiThread(runnable)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun showProgressDialog() {
        (activity as? PokemonIquiiActivity)?.showProgressDialog()
    }

    override fun hideProgressDialog() {
        (activity as PokemonIquiiActivity?)?.hideProgressDialog()
    }

    override fun onInternetNetworkError() {
        hideProgressDialog()
        (activity as PokemonIquiiActivity?)?.showDialogOk(
            R.string.generic_error,
            R.string.generic_errorNetwork,
            null
        )
    }

    override fun showDialogYesNo(
        @StringRes title: Int,
        @StringRes message: Int,
        okListener: View.OnClickListener,
        closeListener: View.OnClickListener?,
        vararg params: String?
    ) {

        (activity as PokemonIquiiActivity?)?.showDialogYesNo(
            title,
            message,
            okListener,
            closeListener,
            *params
        )
    }

    override fun showDialogOk(
        title: Int,
        message: Int?,
        messageString: String?,
        callback: (() -> Unit)?
    ) {
        (activity as PokemonIquiiActivity?)?.showDialogOk(title, message, null, callback)
    }


    open fun runGoBack() = run {
        if (parentFragmentManager.backStackEntryCount == 1) {
            activity?.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            activity?.finish()
            activity?.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        } else {
            popLastFragment()
        }
    }

}

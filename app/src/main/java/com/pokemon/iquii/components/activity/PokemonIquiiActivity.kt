package com.pokemon.iquii.components.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.*
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.AppBarLayout
import com.pokemon.iquii.R
import com.pokemon.iquii.components.actionlistener.PokemonIquiiGenericFragmentListener
import com.pokemon.iquii.components.fragment.PokemonIquiiGenericFragment
import com.pokemon.iquii.components.progressdialog.ProgressDialog
import com.pokemon.iquii.components.settings.Settings
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer
import timber.log.Timber
import timber.log.Timber.d
import timber.log.Timber.w
import java.util.*

abstract class PokemonIquiiActivity : AppCompatActivity(), PokemonIquiiGenericFragmentListener {

    private var progressDialog: ProgressDialog? = null
    private var alertDialog: AlertDialog? = null
    open val activityTitle: String? = null

    private val rootView: View?
        get() = findViewById(R.id.main)

    protected var toolbar: Toolbar? = null
    private var appBar: AppBarLayout? = null
    private var isToolbarTransparent: Boolean = false
    var settings: Settings? = null
        private set

    var toolbarShadow: View? = null
    val thread = Thread()
    var threadAlreadyRunning = false

    override fun onInternetNetworkError() {
        showDialogOk(R.string.generic_error, R.string.generic_errorNetwork, null)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun showDialogOk(
        title: Int,
        message: Int?,
        messageString: String?,
        callback: (() -> Unit)?
    ) {
        var alertDialog: AlertDialog? = null
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.alert_dialog_ok_cancel, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)

        val titleText = dialogView.findViewById(R.id.titleHelp) as TextView
        val messageText = dialogView.findViewById(R.id.messageHelp) as TextView
        val closePopup = dialogView.findViewById(R.id.close) as TextView

        titleText.text = PokemonIQUIILocalizer.get(title, this)
        if (message != null || !messageString.isNullOrEmpty()) {
            if (message != null) {
                messageText.text = PokemonIQUIILocalizer.get(message, this)
            } else {
                messageText.text = messageString
            }
        } else {
            messageText.visibility = View.GONE
        }
        closePopup.text = PokemonIQUIILocalizer.get(R.string.generic_close, this)
        closePopup.setOnClickListener {
            alertDialog?.dismiss()
            callback?.invoke()
        }
        alertDialog = dialogBuilder.create()
        alertDialog?.show()
    }

    fun setupToolbar() {
        appBar = findViewById(R.id.appBarLayout)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.let {
            toolbarShadow = findViewById(R.id.toolbar_shadow)
            if (it.visibility == View.VISIBLE) {
                showToolBarShadow()
            } else {
                hideToolBarShadow()
            }
            setSupportActionBar(it)

            // Show menu icon
            showCorrectMenuIcon()
        }
    }

    private fun showCorrectMenuIcon() {
        val actionBar = supportActionBar
        if (actionBar == null) {
            w("showCorrectMenuIcon: actionBar is null")
            return
        }
    }

    fun setCustomTitleOnActivity(
        title: String?,
        color: Int = resources.getColor(R.color.primaryDark)
    ) {
        title?.let {
            if (TextUtils.isEmpty(it)) {
                super.setTitle(null)
            } else {
                super.setTitle(title)
            }
        } ?: run {
            super.setTitle(null)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomTitleOnActivity(activityTitle)

        setupToolbar()

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.frame_container)
            if (fragment is PokemonIquiiGenericFragment) {
                fragment.setTitle()
            }
            setupToolbar()
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(canPopFragment() || hasParentActivity())
                it.setHomeButtonEnabled(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("NewApi")
    fun hideToolBarShadow() {
        toolbarShadow?.let { it.visibility = View.GONE }
    }

    @SuppressLint("NewApi")
    fun showToolBarShadow() {
        if (isToolbarTransparent) {
            hideToolBarShadow()
            return
        }
    }

    fun showToolBar() {
        toolbar?.let { it.visibility = View.VISIBLE }
        showToolBarShadow()
    }

    private fun hasParentActivity(): Boolean {
        return true
    }

    private fun canPopFragment(): Boolean {
        return supportFragmentManager.backStackEntryCount > 1
    }

    fun pushFragment(fragment: Fragment, containerResId: Int, tag: String? = null) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        tag?.let {
            transaction.replace(containerResId, fragment, tag)
        } ?: run {
            transaction.replace(
                containerResId, fragment,
                fragment.javaClass.simpleName
            )
        }
        transaction.addToBackStack(null)
        transaction.commit()
        invalidateOptionsMenu()
    }

    fun popLastFragment() {
        hideKeyboard()
        supportFragmentManager.popBackStack()
    }

    override fun onSupportNavigateUp(): Boolean {
        executeGoBack()
        return true
    }

    override fun onBackPressed() {
        executeGoBack()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Show the progress dialog
     */
    override fun showProgressDialog() {
        d("showProgressDialog() called")
        if (progressDialog == null) {
            createProgressDialog()
        }
        if (!threadAlreadyRunning) {
            threadAlreadyRunning = true
            thread.run {
                try {
                    runOnUiThread {
                        progressDialog?.showDialog()
                    }
                } catch (e: Exception) {
                    Timber.e("Cannot show the progress dialog " + e.message)
                }
            }
        }
    }


    private fun createProgressDialog() {
        d("createProgressDialog() called")
        progressDialog = ProgressDialog(this@PokemonIquiiActivity)
        // set whether the progressDialog bar is cancelable or not
        progressDialog?.isCancelable = false
    }

    /**
     * Hide the progress dialog
     */
    override fun hideProgressDialog() {
        if (progressDialog == null) {
            createProgressDialog()
        }
        thread.run {
            try {
                runOnUiThread {
                    progressDialog?.hideDialog()
                }
            } catch (e: Exception) {
                Timber.e("Cannot show the progress dialog " + e.message)
            }
            d("hideProgressDialog() called")
            threadAlreadyRunning = false
        }
    }

    /**
     *
     * Declaring vararg param as the last one in list, in a Kotlin function, looks like this:
     *
     * fun format(format: String, vararg params: String)
     *
     * and will be compiled into the corresponding Java code:
     *
     * void format(@NotNull String format, @NotNull String... params)
     *
     * Declaring params after the vararg
     *
     * When vararg parameter is not the last one in list, like this:
     *
     * fun format(format: String, vararg params: String, encoding: String)
     *
     * it gets compiled into corresponding Java code:
     *
     * void format(String format, String[] params, String encoding)
     *
     * In conclusion, if vararg is not the last param it will be compiled as an array of parameter type.
     *
     */
    override fun showDialogYesNo(
        title: Int,
        message: Int,
        okListener: View.OnClickListener,
        closeListener: View.OnClickListener?,
        vararg params: String?
    ) {
        var alertDialog: androidx.appcompat.app.AlertDialog? = null
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.alert_dialog_ok_cancel, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        val titleText = dialogView.findViewById(R.id.titleHelp) as TextView
        val messageText = dialogView.findViewById(R.id.messageHelp) as TextView
        val closePopup = dialogView.findViewById(R.id.close) as TextView
        val okPopup = dialogView.findViewById(R.id.ok) as TextView
        titleText.text = PokemonIQUIILocalizer.get(title, this)
        messageText.text = PokemonIQUIILocalizer.get(message, this, *params)
        closePopup.setOnClickListener {
            alertDialog?.dismiss()
            closeListener.let { listener -> listener?.onClick(it) }
        }
        okPopup.setOnClickListener {
            alertDialog?.dismiss()
            okListener.onClick(it)
        }
        alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    fun executeGoBack() {
        val fragments = supportFragmentManager.fragments
        val genericFragments = LinkedList<PokemonIquiiGenericFragment>()
        for (fragment in fragments) {
            if (fragment is PokemonIquiiGenericFragment) {
                genericFragments.add(fragment)
            }
        }
        val count = genericFragments.size
        if (count == 0) {
            finish()
            return
        }
        val fragment = genericFragments[count - 1]
        fragment.runGoBack()
    }


}

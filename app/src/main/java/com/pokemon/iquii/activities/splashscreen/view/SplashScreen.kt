package com.pokemon.iquii.activities.splashscreen.view

/**
 * This software has been developed by Gioele <br/>
 * <br/>
 * Project: PokemonIQUII <br/>
 * <p> This doc comment should contain a short class description. <p/>
 * <br/>
 * created on: 2022-07-07
 *
 * @author Gioele Sacco Comis
 */

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.pokemon.iquii.MainActivity
import timber.log.Timber

class SplashScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }

    private fun start() {
        val intent: Intent = Intent(this@SplashScreen, MainActivity::class.java)
        if (intent != null) {
            startActivity(intent)
            finish()
        } else {
            Timber.w("Intent not initialized")
        }

    }
}
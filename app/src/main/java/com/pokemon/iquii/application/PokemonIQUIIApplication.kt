package com.pokemon.iquii.application

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
import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import timber.log.Timber

open class PokemonIQUIIApplication : MultiDexApplication() {

    companion object {

        lateinit var instance: PokemonIQUIIApplication

        operator fun get(activity: Activity): PokemonIQUIIApplication {
            return activity.application as PokemonIQUIIApplication
        }

        @Synchronized
        fun storeStaticInstance(app: PokemonIQUIIApplication) {
            instance = app
        }

        @Synchronized
        fun getStaticInstance(): PokemonIQUIIApplication {
            return instance
        }

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG || BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.d("onTerminate() called: %s", this)
    }



}
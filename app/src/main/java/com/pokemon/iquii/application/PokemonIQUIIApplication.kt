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
import com.google.firebase.analytics.FirebaseAnalytics
import com.pokemon.iquii.components.settings.Settings
import com.pokemon.iquii.database.database.DatabaseHelper
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer
import com.pokemoniquiiSdk.PokemonIQUIISdkAPI
import com.pokemoniquiiSdk.PokemonIQUIISdkEnvironment
import timber.log.Timber

open class PokemonIQUIIApplication : MultiDexApplication() {

    companion object {

        lateinit var instance: PokemonIQUIIApplication
        var settings: Settings? = null

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

        settings = Settings(this)
        PokemonIQUIILocalizer.setup(object : PokemonIQUIILocalizer.Delegate{
            override fun get(): PokemonIQUIILocalizer.Strings? {
                return null
            }

            override fun getLocale(): String? {
                return settings?.getLocale()
            }

            override fun saveLocale(locale: String?) {
                settings?.setLocale(locale)
            }

            override fun save(localizedStrings: PokemonIQUIILocalizer.Strings?) {}

        })

        FirebaseAnalytics.getInstance(this);

        if (BuildConfig.DEBUG || BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        settings?.checkPokemonEnvironment()
        DatabaseHelper.init(applicationContext)
        setupPokemonSdkApi(false)

    }

    fun setupPokemonSdkApi(reset: Boolean) {

        val configuration: PokemonIQUIISdkAPI.Configuration.Builder = PokemonIQUIISdkAPI.Configuration.Builder()
            .port(null)
            .apiVersion(2)
            .appName("pokemon")
            .environment(PokemonIQUIISdkEnvironment.Environment.PRODUCTION)
            .host("pokeapi.co")
            .httpsEnabled(true)

       PokemonIQUIISdkAPI.init(this, configuration.build(), reset)
        if (reset) {
            // clear settings
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        Timber.d("onTerminate() called: %s", this)
    }



}
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
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.pokemon.iquii.components.settings.Settings
import com.pokemon.iquii.database.database.DatabaseHelper
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer
import com.pokemoniquiiSdk.PokemonIQUIISdkAPI
import com.pokemoniquiiSdk.PokemonIQUIISdkEnvironment
import timber.log.Timber

open class PokemonIQUIIApplication : MultiDexApplication() {

    //fresco constants
    private val MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB
    private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()
    private val MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4
    private val MAX_DISK_CACHE_SIZE_ON_LOW_DISK_SPACE = 10 * ByteConstants.MB
    private val MAX_DISK_CACHE_SIZE_ON_VERY_LOW_DISK_SPACE = 2 * ByteConstants.MB

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
        storeStaticInstance(this)
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
        initializeFresco()

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

    //region Fresco
    private fun initializeFresco() {
        val config = ImagePipelineConfig.newBuilder(this)
            .setMainDiskCacheConfig(getDefaultMainDiskCacheConfig(this))
            .setBitmapMemoryCacheParamsSupplier(getBitmapMemoryCacheParamsSupplier())
            .setDownsampleEnabled(true)
            .build()
        Fresco.initialize(this, config)
    }

    private fun getBitmapMemoryCacheParamsSupplier(): com.facebook.common.internal.Supplier<MemoryCacheParams>? {
        val bitmapCacheParams = MemoryCacheParams(
            // Max total size of elements in the cache
            MAX_MEMORY_CACHE_SIZE,
            // Max entries in the cache
            Integer.MAX_VALUE,
            // Max total size of elements in eviction queue
            MAX_MEMORY_CACHE_SIZE,
            // Max length of eviction queue
            Integer.MAX_VALUE,
            // Max cache entry size
            Integer.MAX_VALUE)
        return com.facebook.common.internal.Supplier { bitmapCacheParams }
    }

    private fun getDefaultMainDiskCacheConfig(context: Context): DiskCacheConfig {
        return DiskCacheConfig.newBuilder(context)
            .setBaseDirectoryPathSupplier {
                context.applicationContext
                    .cacheDir
            }
            .setBaseDirectoryName("image_cache")
            .setMaxCacheSize(MAX_DISK_CACHE_SIZE.toLong())
            .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_SIZE_ON_LOW_DISK_SPACE.toLong())
            .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_SIZE_ON_VERY_LOW_DISK_SPACE.toLong())
            .build()
    }
    //endregion

    override fun onTerminate() {
        super.onTerminate()
        Timber.d("onTerminate() called: %s", this)
    }



}
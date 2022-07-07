package com.pokemon.iquii.application

import com.facebook.stetho.Stetho

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

class PokemonIQUIIDebugApplication : PokemonIQUIIApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())
    }
}

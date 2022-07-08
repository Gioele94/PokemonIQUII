package com.pokemon.iquii.components.settings

import android.content.Context
import com.pokemon.securitypreferences.PokemonSettings
import com.pokemoniquiiSdk.PokemonIQUIISdkEnvironment

class Settings(context: Context) : PokemonSettings(context, "pokemon_shared_preferences") {

    companion object {
        var instance: Settings? = null
    }

    init {
        if (instance == null) {
            instance = this
        }
    }

    fun getEnvironment(): PokemonIQUIISdkEnvironment.Environment? {
        val value: String? = getString(Key.ENVIRONMENT)
        return try {
            if (value == null) {
                null
            } else {
                PokemonIQUIISdkEnvironment.Environment.valueOf(value)
            }
        } catch (err: Exception) {
            null
        }
    }

    fun setEnvironment(environment: PokemonIQUIISdkEnvironment.Environment) {
        setString(Key.ENVIRONMENT, environment.name)
    }

    fun getServer(): String? {
        return getString(Key.SERVER)
    }

    fun setServer(server: String?) {
        setString(Key.SERVER, server)
    }

    fun getPort(): Int {
        return getInt(Key.PORT)
    }

    fun getUseHttps(): Boolean {
        return getBoolean(Key.USE_HTTPS)
    }

    fun getUseSelfSigned(): Boolean {
        return getBoolean(Key.USE_SELF_SIGNED_CERTIFICATE)
    }

    fun setPort(port: Int) {
        setInt(Key.PORT, port)
    }

    fun setUseHttps(useHttps: Boolean) {
        setBoolean(Key.USE_HTTPS, useHttps)
    }

    fun setUseSelfSigned(useSelfSigned: Boolean) {
        setBoolean(Key.USE_SELF_SIGNED_CERTIFICATE, useSelfSigned)
    }

    fun checkPokemonEnvironment() {

        if (getEnvironment() != null) {
            return
        }

        val currentServer: String? = getServer()
        if (currentServer != null) {
            when (currentServer) {
                "pokeapi.co" -> setEnvironment(PokemonIQUIISdkEnvironment.Environment.PRODUCTION)
                "pokeapi.co" -> setEnvironment(PokemonIQUIISdkEnvironment.Environment.TEST)
                else -> setEnvironment(PokemonIQUIISdkEnvironment.Environment.CUSTOM)
            }
        } else {
            setEnvironment(PokemonIQUIISdkEnvironment.Environment.CUSTOM)
        }

        setUseHttps(false)
        setServer(null)
        setPort(0)
        setUseSelfSigned(false)
    }


    private enum class Key {
        ENVIRONMENT,
        SERVER,
        PORT,
        USE_HTTPS,
        USE_SELF_SIGNED_CERTIFICATE,
    }

}
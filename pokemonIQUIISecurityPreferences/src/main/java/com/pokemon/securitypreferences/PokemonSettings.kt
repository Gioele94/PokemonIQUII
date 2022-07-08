package com.pokemon.securitypreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.pokemon.securitypreferences.secureBelowApi23.SecurePreferencesBelowApi23

open class PokemonSettings(context: Context, settingsFileName: String) {

    private var sharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        EncryptedSharedPreferences.create(
            settingsFileName,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } else {
        SecurePreferencesBelowApi23(context, "AS@/#{[7%!muM75-", settingsFileName)
    }

    /**
     * Get the correct instance of the [PokemonSettings] object:
     * if the app is in debug mode return an [PokemonSettings] object, else return a [PokemonSettings] one
     *
     * @param context the Android context
     * @return this settings instance
     */
    operator fun get(context: Context, settingsFileName: String): PokemonSettings {
        return PokemonSettings(context, settingsFileName)
    }

    @SuppressLint("CommitPrefEdits")
    fun save(listener: OnSaveListener) {
        if (sharedPreferences is SecurePreferencesBelowApi23) {
            listener.onSave((sharedPreferences as SecurePreferencesBelowApi23).edit())
        } else {
            listener.onSave(sharedPreferences.edit())
        }
    }

    fun getBoolean(key: Enum<*>): Boolean {
        return if (sharedPreferences is SecurePreferencesBelowApi23) {
            (sharedPreferences as SecurePreferencesBelowApi23).getBoolean(key.name, false)
        } else {
            sharedPreferences.getBoolean(key.name, false)
        }
    }

    fun getLong(key: Enum<*>): Long {
        return if (sharedPreferences is SecurePreferencesBelowApi23) {
            (sharedPreferences as SecurePreferencesBelowApi23).getLong(key.name, 0)
        } else {
            sharedPreferences.getLong(key.name, 0)
        }
    }

    fun setBoolean(key: Enum<*>, value: Boolean) {
        save(object : OnSaveListener {

            override fun onSave(editor: SecurePreferencesBelowApi23.Editor) {
                editor.putBoolean(key.name, value)
                editor.apply()
            }

            override fun onSave(editor: SharedPreferences.Editor) {
                editor.putBoolean(key.name, value)
                editor.apply()
            }
        })
    }

    fun setLong(key: Enum<*>, value: Long) {
        save(object : OnSaveListener {
            override fun onSave(editor: SecurePreferencesBelowApi23.Editor) {
                editor.putLong(key.name, value)
                editor.apply()
            }

            override fun onSave(editor: SharedPreferences.Editor) {
                editor.putLong(key.name, value)
                editor.apply()
            }
        })
    }

    fun getString(key: Enum<*>): String? {
        return if (sharedPreferences is SecurePreferencesBelowApi23) {
            (sharedPreferences as SecurePreferencesBelowApi23).getString(key.name, null)
        } else {
            sharedPreferences.getString(key.name, null)
        }
    }

    fun getInt(key: Enum<*>): Int {
        return if (sharedPreferences is SecurePreferencesBelowApi23) {
            (sharedPreferences as SecurePreferencesBelowApi23).getInt(key.name, -1)
        } else {
            sharedPreferences.getInt(key.name, -1)
        }
    }

    fun setString(key: Enum<*>, value: String?) {
        save(object : OnSaveListener {
            override fun onSave(editor: SecurePreferencesBelowApi23.Editor) {
                editor.putString(key.name, value)
                editor.apply()
            }

            override fun onSave(editor: SharedPreferences.Editor) {
                editor.putString(key.name, value)
                editor.apply()
            }
        })
    }

    fun setInt(key: Enum<*>, value: Int?) {
        save(object : OnSaveListener {
            override fun onSave(editor: SecurePreferencesBelowApi23.Editor) {
                value?.let { editor.putInt(key.name, it) } ?: run { editor.putInt(key.name, -1) }
                editor.apply()
            }

            override fun onSave(editor: SharedPreferences.Editor) {
                value?.let { editor.putInt(key.name, it) } ?: run { editor.putInt(key.name, -1) }
                editor.apply()
            }
        })
    }

    interface OnSaveListener {
        fun onSave(editor: SecurePreferencesBelowApi23.Editor)
        fun onSave(editor: SharedPreferences.Editor)
    }
}
package com.pokemon.securitypreferences.secureBelowApi23

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Base64
import com.pokemon.securitypreferences.secureBelowApi23.AesCbcWithIntegrity.generateKeyFromPassword
import com.pokemon.securitypreferences.secureBelowApi23.Utils.getDefaultSalt
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.security.GeneralSecurityException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * Wrapper class for Android's [SharedPreferences] interface, which adds a
 * layer of encryption to the persistent storage and retrieval of sensitive
 * key-value pairs of primitive data types.
 *
 *
 * This class provides important - but nevertheless imperfect - protection
 * against simple attacks by casual snoopers. It is crucial to remember that
 * even encrypted data may still be susceptible to attacks, especially on rooted devices
 *
 *
 * Recommended to use with user password, in which case the key will be derived from the password and not stored in the file.
 *
 *
 * TODO: Handle OnSharedPreferenceChangeListener
 */


class SecurePreferencesBelowApi23(
    context: Context,
    secretKey: AesCbcWithIntegrity.SecretKeys?,
    password: String?,
    salt: String?,
    sharedPrefFilename: String?,
    iterationCount: Int
) : SharedPreferences {
    //the backing pref file
    private var sharedPreferences: SharedPreferences? = null

    //secret keys used for enc and dec
    private var keys: AesCbcWithIntegrity.SecretKeys? = null

    //the salt used for enc and dec
    private val salt: String?

    //name of the currently loaded sharedPrefFile, can be null if default
    private var sharedPrefFilename: String? = null

    /**
     * @param context should be ApplicationContext not Activity
     * @param salt    is custom salt you choose for encryption
     */
    constructor(context: Context, salt: String?) : this(
        context,
        null,
        "",
        salt,
        null,
        ORIGINAL_ITERATION_COUNT
    )

    /**
     * @param context        should be ApplicationContext not Activity
     * @param iterationCount The iteration count for the keys generation
     */
    constructor(context: Context, iterationCount: Int) : this(context, "", null, iterationCount)
    /**
     * @param context            should be ApplicationContext not Activity
     * @param password           user password/code used to generate encryption key.
     * @param sharedPrefFilename name of the shared pref file. If null use the default shared prefs
     */
    /**
     * User password defaults to app generated password that's stores obfucated with the other preference values. Also this uses the Default shared pref file
     *
     * @param context should be ApplicationContext not Activity
     */
    constructor(
        context: Context,
        password: String? = "",
        sharedPrefFilename: String? = null
    ) : this(context, password, null, sharedPrefFilename, ORIGINAL_ITERATION_COUNT)

    /**
     * @param context        should be ApplicationContext not Activity
     * @param iterationCount The iteration count for the keys generation
     */
    constructor(
        context: Context,
        password: String?,
        sharedPrefFilename: String?,
        iterationCount: Int
    ) : this(context, null, password, null, sharedPrefFilename, iterationCount)

    /**
     * @param context            should be ApplicationContext not Activity
     * @param secretKey          that you've generated
     * @param sharedPrefFilename name of the shared pref file. If null use the default shared prefs
     */
    constructor(
        context: Context,
        secretKey: AesCbcWithIntegrity.SecretKeys?,
        sharedPrefFilename: String?
    ) : this(context, secretKey, null, null, sharedPrefFilename, 0)

    /**
     * @param context        should be ApplicationContext not Activity
     * @param iterationCount The iteration count for the keys generation
     */
    constructor(
        context: Context,
        password: String?,
        salt: String?,
        sharedPrefFilename: String?,
        iterationCount: Int
    ) : this(context, null, password, salt, sharedPrefFilename, iterationCount)

    /**
     * if a prefFilename is not defined the getDefaultSharedPreferences is used.
     *
     * @param context should be ApplicationContext not Activity
     * @return
     */
    private fun getSharedPreferenceFile(
        context: Context,
        prefFilename: String?
    ): SharedPreferences {
        sharedPrefFilename = prefFilename
        return if (TextUtils.isEmpty(prefFilename)) {
            PreferenceManager.getDefaultSharedPreferences(context)
        } else {
            context.getSharedPreferences(prefFilename, Context.MODE_PRIVATE)
        }
    }

    /**
     * nulls in memory keys
     */
    fun destroyKeys() {
        keys = null
    }

    /**
     * Uses device and application values to generate the pref key for the encryption key
     *
     * @param context        should be ApplicationContext not Activity
     * @param iterationCount The iteration count for the keys generation
     * @return String to be used as the AESkey Pref key
     * @throws GeneralSecurityException if something goes wrong in generation
     */
    @Throws(GeneralSecurityException::class)
    private fun generateAesKeyName(context: Context, iterationCount: Int): String? {
        val password = context.packageName
        val salt = getSalt(context)!!.toByteArray()
        val generatedKeyName: AesCbcWithIntegrity.SecretKeys =
            generateKeyFromPassword(password, salt)
        return hashPrefKey(generatedKeyName.toString())
    }

    /**
     * Gets the salt value
     *
     * @param context used for accessing hardware serial number (if accessible) or the DeviceId in case salt is not set
     * @return
     */
    private fun getSalt(context: Context): String? {
        return if (TextUtils.isEmpty(salt)) {
            Timber.tag(TAG)
                .w("Using the default generated Salt, it's more forward compatible to pass your own salt or use password")
            getDefaultSalt(context)
        } else {
            salt
        }
    }

    private fun encrypt(cleartext: String?): String? {
        if (TextUtils.isEmpty(cleartext)) {
            return cleartext
        }
        try {
            return cleartext?.let { cleartext ->
                keys?.let { keys ->
                    AesCbcWithIntegrity.encrypt(cleartext, keys).toString()
                }
            }
        } catch (e: GeneralSecurityException) {
            if (isLoggingEnabled) {
                Timber.tag(TAG).w(e, "encrypt")
            }
            return null
        } catch (e: UnsupportedEncodingException) {
            if (isLoggingEnabled) {
                Timber.tag(TAG).w(e, "encrypt")
            }
        }
        return null
    }

    /**
     * @param ciphertext
     * @return decrypted plain text, unless decryption fails, in which case null
     */
    private fun decrypt(ciphertext: String?): String? {
        if (TextUtils.isEmpty(ciphertext)) {
            return ciphertext
        }
        try {
            val cipherTextIvMac: AesCbcWithIntegrity.CipherTextIvMac? =
                ciphertext?.let { AesCbcWithIntegrity.CipherTextIvMac(it) }
            return cipherTextIvMac?.let { cipherTextIvMac ->
                keys?.let { keys ->
                    AesCbcWithIntegrity.decryptString(
                        cipherTextIvMac,
                        keys
                    )
                }
            }
        } catch (e: GeneralSecurityException) {
            if (isLoggingEnabled) {
                Timber.tag(TAG).w(e, "decrypt")
            }
        } catch (e: UnsupportedEncodingException) {
            if (isLoggingEnabled) {
                Timber.tag(TAG).w(e, "decrypt")
            }
        }
        return null
    }

    /**
     * Return the map of all the objects that are stored in the shared preferences.
     * @return Map<K></K>, V> where,
     * K is the hashed key
     * V is the decrypted value. The value will be of type String or StringSet only.
     * All other types apart from StringSet will be returned as String.
     */
    override fun getAll(): Map<String, *>? {
        //wont be null as per http://androidxref.com/5.1.0_r1/xref/frameworks/base/core/java/android/app/SharedPreferencesImpl.java
        val encryptedMap = sharedPreferences?.all
        val decryptedMap: MutableMap<String, Any?>? = encryptedMap?.size?.let { HashMap(it) }
        encryptedMap?.let { encryptedMap ->
            for ((key, cipherText) in encryptedMap) {
                // Check if the data stored is a StringSet
                if (cipherText == null || cipherText == keys.toString()) {
                    continue
                }
                try {
                    val stringSet = getDecryptedStringSet(cipherText)
                    if (stringSet != null) {
                        decryptedMap?.set(key, stringSet)
                    } else {
                        decryptedMap?.set(key, decrypt(cipherText.toString()))
                    }
                } catch (e: Exception) {
                    if (isLoggingEnabled) {
                        Timber.tag(TAG).w(e, "error during getAll")
                    }
                    // Ignore issues that unencrypted values and use instead raw cipher text string
                    decryptedMap?.set(key, cipherText.toString())
                }
            }
        }
        return decryptedMap
    }

    override fun getString(key: String?, defaultValue: String?): String? {
        val encryptedValue = sharedPreferences?.getString(
            hashPrefKey(key), null
        )
        val decryptedValue = decrypt(encryptedValue)
        return if (encryptedValue != null && decryptedValue != null) {
            decryptedValue
        } else {
            defaultValue
        }
    }

    /**
     * Added to get a values as as it can be useful to store values that are
     * already encrypted and encoded
     *
     * @param key          pref key
     * @param defaultValue
     * @return Encrypted value of the key or the defaultValue if no value exists
     */
    fun getEncryptedString(key: String, defaultValue: String?): String? {
        val encryptedValue = sharedPreferences?.getString(hashPrefKey(key), null)
        return encryptedValue ?: defaultValue
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun getStringSet(key: String?, defaultValues: Set<String>?): Set<String>? {
        val encryptedSet = sharedPreferences?.getStringSet(hashPrefKey(key), null)
            ?: return defaultValues
        val decryptedSet: MutableSet<String> = HashSet(encryptedSet.size)
        for (encryptedValue in encryptedSet) {
            decrypt(encryptedValue)?.let { decryptedSet.add(it) }
        }
        return decryptedSet
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        val encryptedValue = sharedPreferences?.getString(hashPrefKey(key), null)
            ?: return defaultValue
        return try {
            decrypt(encryptedValue)?.toInt()?.let { it } ?: run { -1 }
        } catch (e: NumberFormatException) {
            throw ClassCastException(e.message)
        }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        val encryptedValue = sharedPreferences?.getString(hashPrefKey(key), null)
            ?: return defaultValue
        return try {
            decrypt(encryptedValue)?.toLong()?.let { it } ?: run { -1L }
        } catch (e: NumberFormatException) {
            throw ClassCastException(e.message)
        }
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        val encryptedValue = sharedPreferences?.getString(hashPrefKey(key), null)
            ?: return defaultValue
        return try {
            decrypt(encryptedValue)?.toFloat() ?: run { -1f }
        } catch (e: NumberFormatException) {
            throw ClassCastException(e.message)
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val encryptedValue = sharedPreferences?.getString(hashPrefKey(key), null)
            ?: return defaultValue
        return try {
            java.lang.Boolean.parseBoolean(decrypt(encryptedValue))
        } catch (e: NumberFormatException) {
            throw ClassCastException(e.message)
        }
    }

    override fun contains(key: String): Boolean {
        return sharedPreferences?.contains(hashPrefKey(key))?.let { it } ?: run { false }
    }

    /**
     * Cycle through the unencrypt all the current prefs to mem cache, clear, then encypt with key generated from new password.
     * This method can be used if switching from the generated key to a key derived from user password
     *
     *
     * Note: the pref keys will remain the same as they are SHA256 hashes.
     *
     * @param newPassword
     * @param context        should be ApplicationContext not Activity
     * @param iterationCount The iteration count for the keys generation
     */
    @SuppressLint("CommitPrefEdits")
    @Throws(GeneralSecurityException::class)
    fun handlePasswordChange(newPassword: String?, context: Context, iterationCount: Int) {
        val salt = getSalt(context)!!.toByteArray()
        val newKey: AesCbcWithIntegrity.SecretKeys? =
            newPassword?.let { generateKeyFromPassword(it, salt) }
        val allOfThePrefs = sharedPreferences?.all
        val unencryptedPrefs: MutableMap<String, String?>? =
            allOfThePrefs?.size?.let { HashMap(it) }
        //iterate through the current prefs unencrypting each one
        allOfThePrefs?.keys?.let { keys ->
            for (prefKey in keys) {
                val prefValue = allOfThePrefs[prefKey]
                if (prefValue is String) { //all the encrypted values will be Strings
                    val plainTextPrefValue = decrypt(prefValue)
                    unencryptedPrefs?.set(prefKey, plainTextPrefValue)
                }
            }
        }
        //destroy and clear the current pref file
        destroyKeys()
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.commit()
        //refresh the sharedPreferences object ref: I found it was retaining old ref/values
        sharedPreferences = null
        sharedPreferences = getSharedPreferenceFile(context, sharedPrefFilename)
        //assign new key
        keys = newKey
        val updatedEditor = sharedPreferences?.edit()
        //iterate through the unencryptedPrefs encrypting each one with new key
        val unencryptedPrefsKeys: Iterator<String>? = unencryptedPrefs?.keys?.iterator()
        while (unencryptedPrefsKeys?.hasNext()?.let { it } ?: run { false }) {
            val prefKey = unencryptedPrefsKeys?.next()
            val prefPlainText = unencryptedPrefs?.get(prefKey)
            updatedEditor?.putString(prefKey, encrypt(prefPlainText))
        }
        updatedEditor?.commit()
    }

    @Throws(GeneralSecurityException::class)
    fun handlePasswordChange(newPassword: String?, context: Context) {
        handlePasswordChange(newPassword, context, ORIGINAL_ITERATION_COUNT)
    }

    override fun edit(): Editor {
        return Editor()
    }

    /**
     * Wrapper for Android's [android.content.SharedPreferences.Editor].
     *
     *
     * Used for modifying values in a [SecurePreferencesBelowApi23] object. All
     * changes you make in an editor are batched, and not copied back to the
     * original [SecurePreferencesBelowApi23] until you call [.commit] or
     * [.apply].
     */
    inner class Editor : SharedPreferences.Editor {
        private val mEditor: SharedPreferences.Editor? = sharedPreferences?.edit()
        override fun putString(key: String?, value: String?): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), encrypt(value))
            return this
        }

        /**
         * This is useful for storing values that have be encrypted by something
         * else or for testing
         *
         * @param key   - encrypted as usual
         * @param value will not be encrypted
         * @return
         */
        fun putUnencryptedString(
            key: String,
            value: String?
        ): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), value)
            return this
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        override fun putStringSet(key: String?, values: Set<String>?): SharedPreferences.Editor {
            val encryptedValues: MutableSet<String?>? = values?.size?.let { HashSet(it) }
            if (values != null) {
                for (value in values) {
                    encryptedValues?.add(encrypt(value))
                }
            }
            mEditor?.putStringSet(hashPrefKey(key), encryptedValues)
            return this
        }

        override fun putInt(key: String, value: Int): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), encrypt(value.toString()))
            return this
        }

        override fun putLong(key: String, value: Long): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), encrypt(value.toString()))
            return this
        }

        override fun putFloat(key: String, value: Float): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), encrypt(value.toString()))
            return this
        }

        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
            mEditor?.putString(hashPrefKey(key), encrypt(java.lang.Boolean.toString(value)))
            return this
        }

        override fun remove(key: String): SharedPreferences.Editor {
            mEditor?.remove(hashPrefKey(key))
            return this
        }

        override fun clear(): SharedPreferences.Editor {
            mEditor?.clear()
            return this
        }

        override fun commit(): Boolean {
            return mEditor?.commit()?.let { it } ?: run { false }
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        override fun apply() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                mEditor?.apply()
            } else {
                commit()
            }
        }
    }

    override fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        sharedPreferences?.registerOnSharedPreferenceChangeListener(listener)
    }

    /**
     * @param listener    OnSharedPreferenceChangeListener
     * @param decryptKeys Callbacks receive the "key" parameter decrypted
     */
    fun registerOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener, decryptKeys: Boolean
    ) {
        if (!decryptKeys) {
            registerOnSharedPreferenceChangeListener(listener)
        }
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        sharedPreferences?.unregisterOnSharedPreferenceChangeListener(listener)
    }

    /**
     * Method to get the decrypted string set from a cipher text
     * @param cipherText The cipher text from which the string set needs to be retrieved
     * @return null if the cipherText is not a valid StringSet, or any of the values in the set are not strings.
     * Else, it will return the StringSet with the decrypted values.
     */
    private fun getDecryptedStringSet(cipherText: Any?): Set<String?>? {
        if (cipherText == null) {
            return null
        }
        val isSet = cipherText is Set<*>
        if (!isSet) {
            return null
        }
        val encryptedSet = cipherText as Set<*>
        val decryptedSet: MutableSet<String?> = HashSet()
        for (`object` in encryptedSet) {
            if (`object` is String) {
                decryptedSet.add(decrypt(`object`))
            } else {
                return null
            }
        }
        return decryptedSet
    }

    companion object {
        private const val ORIGINAL_ITERATION_COUNT = 10000
        var isLoggingEnabled = false
        private val TAG = SecurePreferencesBelowApi23::class.java.name

        /**
         * The Pref keys must be same each time so we're using a hash to obscure the stored value
         *
         * @param prefKey
         * @return SHA-256 Hash of the preference key
         */
        fun hashPrefKey(prefKey: String?): String? {
            val digest: MessageDigest
            try {
                digest = MessageDigest.getInstance("SHA-256")
                val bytes = prefKey?.toByteArray(charset("UTF-8"))
                bytes?.size?.let { digest.update(bytes, 0, it) }
                return Base64.encodeToString(digest.digest(), AesCbcWithIntegrity.BASE64_FLAGS)
            } catch (e: NoSuchAlgorithmException) {
                if (isLoggingEnabled) {
                    Timber.tag(TAG).w(e, "Problem generating hash")
                }
            } catch (e: UnsupportedEncodingException) {
                if (isLoggingEnabled) {
                    Timber.tag(TAG).w(e, "Problem generating hash")
                }
            }
            return null
        }

    }

    init {
        if (sharedPreferences == null) {
            sharedPreferences = getSharedPreferenceFile(context, sharedPrefFilename)
        }
        this.salt = salt
        if (secretKey != null) {
            keys = secretKey
        } else if (TextUtils.isEmpty(password)) { // Initialize or create encryption key
            try {
                val key = generateAesKeyName(context, iterationCount)
                val keyAsString = sharedPreferences!!.getString(key, null)
                if (keyAsString == null) {
                    keys = AesCbcWithIntegrity.generateKey()
                    //saving new key
                    val committed =
                        sharedPreferences!!.edit().putString(key, keys.toString()).commit()
                    if (!committed) {
                        Timber.tag(TAG).w("Key not committed to prefs")
                    }
                } else {
                    keys = AesCbcWithIntegrity.keys(keyAsString)
                }
                if (keys == null) {
                    throw GeneralSecurityException("Problem generating Key")
                }
            } catch (e: GeneralSecurityException) {
                if (isLoggingEnabled) {
                    Timber.e("Error init:%s", e.message)
                }
                throw IllegalStateException(e)
            }
        } else { //use the password to generate the key
            try {
                val saltBytes = getSalt(context)!!.toByteArray()
                keys = password?.let { generateKeyFromPassword(it, saltBytes) }
                if (keys == null) {
                    throw GeneralSecurityException("Problem generating Key From Password")
                }
            } catch (e: GeneralSecurityException) {
                if (isLoggingEnabled) {
                    Timber.tag(TAG).e("Error init using user password:%s", e.message)
                }
                throw IllegalStateException(e)
            }
        }
    }
}
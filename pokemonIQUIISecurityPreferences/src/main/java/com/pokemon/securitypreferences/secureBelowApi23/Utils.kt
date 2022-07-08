package com.pokemon.securitypreferences.secureBelowApi23

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils


object Utils {
    /**
     * This method is here for backwards compatibility reasons. Recommend supplying your own Salt
     *
     * @param context
     * @return Consistent between app restarts, device restarts, factory resets,
     * however cannot be guaranteed on OS updates.
     */
    @SuppressLint("MissingPermission")
    fun getDefaultSalt(context: Context): String { //Android Q removes all access to Serial, fallback to Settings.Secure.ANDROID_ID
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            getSecureDeviceId(context)
        } else {
            getDeviceSerialNumber(context)
        }
    }

    @SuppressLint("HardwareIds")
    private fun getSecureDeviceId(context: Context): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    /**
     * Gets the hardware serial number of this device. This only for backwards compatibility
     *
     * @return serial number or Settings.Secure.ANDROID_ID if not available.
     */
    @SuppressLint("MissingPermission", "HardwareIds")
    private fun getDeviceSerialNumber(context: Context): String {
        return try {
            val deviceSerial: String = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
                Build.getSerial()
            } else {
                Build.SERIAL
            }
            if (TextUtils.isEmpty(deviceSerial)) {
                getSecureDeviceId(context)
            } else {
                deviceSerial
            }
        } catch (ignored: Exception) { // Fall back to Android_ID
            getSecureDeviceId(context)
        }
    }
}
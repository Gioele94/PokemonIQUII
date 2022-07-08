package com.pokemoniquiiSdk

import android.content.Context
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

object PokemonIQUIISdkAPI {

    open class Configuration constructor(builder: Builder) {

        val environment = PokemonIQUIISdkEnvironment.get(builder)
        val apiVersion: Int? = builder.apiVersion ?: 1
        val appName: String? = builder.appName

        fun getBaseUrl(type: PokemonIQUIIService.Type): String? {
            var baseUrl = (if (environment.httpsEnabled) "http" else "http") + "://"
            if ((baseUrl + environment.host).toHttpUrlOrNull() != null) {
                baseUrl += configuration.environment.host
                if (environment.port != null && environment.host.isNotEmpty()) {
                    baseUrl += ":${environment.port}"
                }
                baseUrl += "/"
                if (apiVersion != null && apiVersion > 0) {
                    baseUrl += "api/v${apiVersion}"
                    baseUrl += "/"
                }
            }
            return baseUrl
        }

        /**
         * @return Returns a list of strings containing one or more certificate PEM. The list is not empty only if the
         * connection is in HTTPS
         */

        fun getSelfSignedCertificates(): List<String?>? {
            val certificates = configuration.environment.selfSignedCertificates
            if (configuration.environment.httpsEnabled && certificates != null) {
                return certificates
            }
            return ArrayList()
        }


        class Builder {

            var environment = PokemonIQUIISdkEnvironment.Environment.PRODUCTION
            var httpsEnabled: Boolean = false
            var selfSignedCertificates: List<String?>? = null
            var host: String? = null
            var port: String? = null
            var appName: String? = null
            var apiVersion: Int? = null

            fun environment(environment: PokemonIQUIISdkEnvironment.Environment): Builder {
                this.environment = environment
                return this
            }

            fun environment(
                httpsEnabled: Boolean,
                host: String?,
                port: String?,
                selfSignedCertificates: List<String?>? = null
            ): Builder {
                this.environment = PokemonIQUIISdkEnvironment.Environment.CUSTOM
                this.httpsEnabled = httpsEnabled
                this.host = host
                this.port = port
                this.selfSignedCertificates = selfSignedCertificates
                return this
            }

            fun httpsEnabled(httpsEnabled: Boolean): Builder {
                this.httpsEnabled = httpsEnabled
                return this
            }

            fun selfSignedCertificates(selfSignedCertificates: List<String?>?): Builder {
                this.selfSignedCertificates = selfSignedCertificates
                return this
            }

            fun host(host: String): Builder {
                this.host = host
                return this
            }

            fun port(port: String?): Builder {
                this.port = port
                return this
            }

            fun appName(appName: String?): Builder {
                this.appName = appName
                return this
            }

            fun apiVersion(apiVersion: Int?): Builder {
                this.apiVersion = apiVersion
                return this
            }

            fun build(): Configuration {
                return Configuration(this)
            }
        }
    }

    lateinit var configuration: Configuration

    fun init(context: Context?, configuration: Configuration, reset: Boolean) {
        PokemonIQUIISdkAPI.configuration = configuration
        if (reset) {
            clearSession()
            PokemonIQUIIService.reset()
        }
    }

    fun reset(configuration: Configuration) {
        PokemonIQUIISdkAPI.configuration = configuration
        PokemonIQUIIService.reset()
    }

    private fun clearSession() {
        //TODO:: Clear auth session
    }

}

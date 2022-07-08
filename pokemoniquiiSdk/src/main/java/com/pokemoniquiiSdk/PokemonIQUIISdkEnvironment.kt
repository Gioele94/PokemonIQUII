package com.pokemoniquiiSdk


class PokemonIQUIISdkEnvironment(
    val host: String,
    val httpsEnabled: Boolean = false,
    val port: String? = null,
    val selfSignedCertificates: List<String?>? = null
) {

    enum class Environment {
        PRODUCTION,
        TEST,
        CUSTOM
    }

    companion object {

        fun get(builder: PokemonIQUIISdkAPI.Configuration.Builder): PokemonIQUIISdkEnvironment {
            return when (builder.environment) {
                Environment.PRODUCTION -> PokemonIQUIISdkEnvironment("pokeapi.co")
                Environment.TEST -> PokemonIQUIISdkEnvironment("pokeapi.co")
                else -> PokemonIQUIISdkEnvironment(
                    builder.host!!,
                    builder.httpsEnabled,
                    builder.port,
                    builder.selfSignedCertificates
                )
            }
        }
    }
}
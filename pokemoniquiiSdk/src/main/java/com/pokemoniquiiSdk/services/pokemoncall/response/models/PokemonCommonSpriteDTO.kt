package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName

open class PokemonCommonSpriteDTO {

    @SerializedName("back_default")
    val backDefault: String? = null

    @SerializedName("back_female")
    val backFemale: String? = null

    @SerializedName("back_shiny")
    val backShiny: String? = null

    @SerializedName("back_shiny_female")
    val backShinyFemale: String? = null

    @SerializedName("front_default")
    val frontDefault: String? = null

    @SerializedName("front_female")
    val frontFemale: String? = null

    @SerializedName("front_shiny")
    val frontShiny: String? = null

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null

    @SerializedName("back_shiny_transparent")
    val backShinyTransparent: String? = null

    @SerializedName("back_transparent")
    val backTransparent: String? = null

    @SerializedName("front_shiny_transparent")
    val frontShinyTransparent: String? = null

    @SerializedName("front_transparent")
    val frontTransparent: String? = null

}
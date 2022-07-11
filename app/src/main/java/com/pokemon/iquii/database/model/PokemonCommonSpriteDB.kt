package com.pokemon.iquii.database.model

import java.io.Serializable

open class PokemonCommonSpriteDB: Serializable {

    var backDefault: String? = null
    var backFemale: String? = null
    var backShiny: String? = null
    var backShinyFemale: String? = null
    var frontDefault: String? = null
    var frontFemale: String? = null
    var frontShiny: String? = null
    var frontShinyFemale: String? = null
    var backShinyTransparent: String? = null
    var backTransparent: String? = null
    var frontShinyTransparent: String? = null
    var frontTransparent: String? = null

}
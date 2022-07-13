package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class PokemonCommonStatDTO: Serializable {

    var name: StatNameDTO = StatNameDTO.HP
    var url: String = ""

}

enum class StatNameDTO(val value: String){

    @SerializedName("hp")
    HP("hp"),

    @SerializedName("attack")
    ATTACK("attack"),

    @SerializedName("defense")
    DEFENSE("defense"),

    @SerializedName("special-attack")
    SPECIAL_ATTACK("special-attack"),

    @SerializedName("special-defense")
    SPECIAL_DEFENSE("special-defense"),

    @SerializedName("speed")
    SPEED("speed")

}
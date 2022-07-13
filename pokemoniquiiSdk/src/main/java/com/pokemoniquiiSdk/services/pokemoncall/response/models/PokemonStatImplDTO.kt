package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName

class PokemonStatImplDTO {

    @SerializedName("base_stat")
    val baseStat: Int = 0
    val effort: Int = 0
    val stat: PokemonCommonStatDTO = PokemonCommonStatDTO()
}
package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName

class PokemonAbilityDTO {

    val ability: PokemonCommonObjectDTO = PokemonCommonObjectDTO()

    @SerializedName("is_hidden")
    val isHidded: Boolean = false

    val slot: Int = 0

}
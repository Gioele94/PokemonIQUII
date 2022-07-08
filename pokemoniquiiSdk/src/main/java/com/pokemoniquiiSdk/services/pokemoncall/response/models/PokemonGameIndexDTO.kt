package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName

class PokemonGameIndexDTO {

    @SerializedName("game_index")
    val gameIndex: Int = 0

    val version: PokemonCommonObjectDTO = PokemonCommonObjectDTO()

}
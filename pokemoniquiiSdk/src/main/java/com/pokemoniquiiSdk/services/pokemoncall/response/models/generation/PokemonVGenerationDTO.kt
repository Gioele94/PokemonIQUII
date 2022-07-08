package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonVGenerationDTO: PokemonCommonGenerationDTO() {

    @SerializedName("black-white")
    val blackWhite: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
}
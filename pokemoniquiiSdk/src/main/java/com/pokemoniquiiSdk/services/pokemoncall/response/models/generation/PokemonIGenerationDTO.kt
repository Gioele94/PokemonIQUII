package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIGenerationDTO: PokemonCommonGenerationDTO() {

    @SerializedName("red-blue")
    val redBlue: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    val yellow: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
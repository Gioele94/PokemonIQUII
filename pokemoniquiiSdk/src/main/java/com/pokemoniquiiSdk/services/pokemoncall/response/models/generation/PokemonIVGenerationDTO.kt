package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIVGenerationDTO: PokemonCommonGenerationDTO() {

    val platinum: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("diamond-pearl")
    val diamondPearl: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonVIGenerationDTO: PokemonCommonGenerationDTO() {

    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("x-y")
    val xy: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
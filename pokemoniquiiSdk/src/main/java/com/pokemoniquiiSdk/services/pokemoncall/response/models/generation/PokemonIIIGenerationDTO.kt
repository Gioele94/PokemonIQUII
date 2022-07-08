package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIIIGenerationDTO: PokemonCommonGenerationDTO() {

    val emerald: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("ruby-sapphire")
    val rubySapphire: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
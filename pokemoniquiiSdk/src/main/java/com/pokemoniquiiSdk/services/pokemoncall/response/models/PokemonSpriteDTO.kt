package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.generation.*

class PokemonSpriteDTO : PokemonCommonSpriteDTO() {

    val other: PokemonSpriteOtherDTO = PokemonSpriteOtherDTO()

}

class PokemonSpriteOtherDTO {

    @SerializedName("dream_world")
    val dreamWorld: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    val home: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    @SerializedName("official-artwork")
    val officialArtwork: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

    val versions: PokemonSpriteGenerationDTO = PokemonSpriteGenerationDTO()

}

class PokemonSpriteGenerationDTO {

    @SerializedName("generation-i")
    val generationI: PokemonIGenerationDTO? = null

    @SerializedName("generation-ii")
    val generationII: PokemonIIGenerationDTO? = null

    @SerializedName("generation-iii")
    val generationIIII: PokemonIIIGenerationDTO? = null

    @SerializedName("generation-iv")
    val generationIV: PokemonIVGenerationDTO? = null

    @SerializedName("generation-v")
    val generationV: PokemonVGenerationDTO? = null

    @SerializedName("generation-vi")
    val generationVI: PokemonVIGenerationDTO? = null

    @SerializedName("generation-vii")
    val generationVII: PokemonVIIGenerationDTO? = null

    @SerializedName("generation-viii")
    val generationVIII: PokemonVIIGenerationDTO? = null


}
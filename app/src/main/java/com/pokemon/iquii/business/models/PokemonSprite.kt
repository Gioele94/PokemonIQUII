package com.pokemon.iquii.business.models

import com.pokemoniquiiSdk.services.pokemoncall.response.models.generation.*

class PokemonSpriteDTO : PokemonCommonSprite() {

    val other: PokemonSpriteOther = PokemonSpriteOther()

}

class PokemonSpriteOther {

    val dreamWorld: PokemonCommonSprite = PokemonCommonSprite()
    val home: PokemonCommonSprite = PokemonCommonSprite()
    val officialArtwork: PokemonCommonSprite = PokemonCommonSprite()
    val versions: PokemonSpriteGenerationDTO = PokemonSpriteGenerationDTO()

}

class PokemonSpriteGenerationDTO {

    val generationI: PokemonIGenerationDTO? = null
    val generationII: PokemonIIGenerationDTO? = null
    val generationIIII: PokemonIIIGenerationDTO? = null
    val generationIV: PokemonIVGenerationDTO? = null
    val generationV: PokemonVGenerationDTO? = null
    val generationVI: PokemonVIGenerationDTO? = null
    val generationVII: PokemonVIIGenerationDTO? = null
    val generationVIII: PokemonVIIGenerationDTO? = null


}
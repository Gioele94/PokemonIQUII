package com.pokemon.iquii.business.models

import com.pokemon.iquii.business.models.generation.*
import java.io.Serializable

class PokemonSprite : PokemonCommonSprite(), Serializable {

    var other: PokemonSpriteOther = PokemonSpriteOther()

}

class PokemonSpriteOther {

    var dreamWorld: PokemonCommonSprite = PokemonCommonSprite()
    var home: PokemonCommonSprite = PokemonCommonSprite()
    var officialArtwork: PokemonCommonSprite = PokemonCommonSprite()
    var versions: PokemonSpriteGeneration = PokemonSpriteGeneration()

}

class PokemonSpriteGeneration {

    var generationI: PokemonIGeneration? = null
    var generationII: PokemonIIGeneration? = null
    var generationIII: PokemonIIIGeneration? = null
    var generationIV: PokemonIVGeneration? = null
    var generationV: PokemonVGeneration? = null
    var generationVI: PokemonVIGeneration? = null
    var generationVII: PokemonVIIGeneration? = null
    var generationVIII: PokemonVIIIGeneration? = null

}
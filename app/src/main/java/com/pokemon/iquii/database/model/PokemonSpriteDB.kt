package com.pokemon.iquii.database.model

import com.pokemon.iquii.database.model.generation.*
import java.io.Serializable

class PokemonSpriteDB : PokemonCommonSpriteDB(), Serializable {

    var other: PokemonSpriteOtherDB = PokemonSpriteOtherDB()

}

class PokemonSpriteOtherDB {

    var dreamWorld: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var home: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var officialArtwork: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var versions: PokemonSpriteGenerationDB = PokemonSpriteGenerationDB()

}

class PokemonSpriteGenerationDB {

    var generationI: PokemonIGenerationDB? = null
    var generationII: PokemonIIGenerationDB? = null
    var generationIII: PokemonIIIGenerationDB? = null
    var generationIV: PokemonIVGenerationDB? = null
    var generationV: PokemonVGenerationDB? = null
    var generationVI: PokemonVIGenerationDB? = null
    var generationVII: PokemonVIIGenerationDB? = null
    var generationVIII: PokemonVIIIGenerationDB? = null

}
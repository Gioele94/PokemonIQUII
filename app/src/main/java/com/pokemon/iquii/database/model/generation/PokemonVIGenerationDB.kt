package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonVIGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var omegarubyAlphasapphire: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var xy: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
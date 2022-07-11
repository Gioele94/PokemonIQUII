package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonIIGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var crystal: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var gold: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var silver: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
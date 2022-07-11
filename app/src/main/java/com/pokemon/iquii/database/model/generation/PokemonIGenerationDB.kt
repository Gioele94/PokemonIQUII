package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonIGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var redBlue: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var yellow: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
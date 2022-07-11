package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonIIIGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var emerald: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var fireredLeafgreen: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var rubySapphire: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
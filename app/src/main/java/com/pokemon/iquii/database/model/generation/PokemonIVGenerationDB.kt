package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonIVGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var platinum: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var diamondPearl: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
    var heartgoldSoulsilver: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
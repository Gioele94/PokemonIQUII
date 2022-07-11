package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonVGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var blackWhite: PokemonCommonSpriteDB = PokemonCommonSpriteDB()
}
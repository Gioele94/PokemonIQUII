package com.pokemon.iquii.database.model.generation

import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import java.io.Serializable

class PokemonVIIGenerationDB : PokemonCommonGenerationDB(), Serializable {

    var ultraSunUltraMoon: PokemonCommonSpriteDB = PokemonCommonSpriteDB()

}
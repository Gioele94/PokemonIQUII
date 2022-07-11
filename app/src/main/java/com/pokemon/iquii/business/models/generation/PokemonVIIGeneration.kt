package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonVIIGeneration : PokemonCommonGeneration(), Serializable {

    var ultraSunUltraMoon: PokemonCommonSprite = PokemonCommonSprite()

}
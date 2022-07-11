package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonVGeneration : PokemonCommonGeneration(), Serializable {

    var blackWhite: PokemonCommonSprite = PokemonCommonSprite()
}
package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonIGeneration : PokemonCommonGeneration(), Serializable {

    var redBlue: PokemonCommonSprite = PokemonCommonSprite()
    var yellow: PokemonCommonSprite = PokemonCommonSprite()

}
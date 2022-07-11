package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonVIGeneration : PokemonCommonGeneration(), Serializable {

    var omegarubyAlphasapphire: PokemonCommonSprite = PokemonCommonSprite()
    var xy: PokemonCommonSprite = PokemonCommonSprite()

}
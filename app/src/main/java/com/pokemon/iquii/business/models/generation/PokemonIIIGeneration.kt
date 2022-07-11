package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonIIIGeneration : PokemonCommonGeneration(), Serializable {

    var emerald: PokemonCommonSprite = PokemonCommonSprite()
    var fireredLeafgreen: PokemonCommonSprite = PokemonCommonSprite()
    var rubySapphire: PokemonCommonSprite = PokemonCommonSprite()

}
package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonIIGeneration : PokemonCommonGeneration(), Serializable {

    var crystal: PokemonCommonSprite = PokemonCommonSprite()
    var gold: PokemonCommonSprite = PokemonCommonSprite()
    var silver: PokemonCommonSprite = PokemonCommonSprite()

}
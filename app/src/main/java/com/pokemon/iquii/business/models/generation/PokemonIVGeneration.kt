package com.pokemon.iquii.business.models.generation

import com.pokemon.iquii.business.models.PokemonCommonSprite
import java.io.Serializable

class PokemonIVGeneration : PokemonCommonGeneration(), Serializable {

    var platinum: PokemonCommonSprite = PokemonCommonSprite()
    var diamondPearl: PokemonCommonSprite = PokemonCommonSprite()
    var heartgoldSoulsilver: PokemonCommonSprite = PokemonCommonSprite()

}
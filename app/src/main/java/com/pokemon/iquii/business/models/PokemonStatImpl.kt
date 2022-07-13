package com.pokemon.iquii.business.models

import java.io.Serializable

class PokemonStatImpl: Serializable {

    var baseStat: Int = 0
    var effort: Int = 0
    var stat: PokemonCommonStat = PokemonCommonStat()
}
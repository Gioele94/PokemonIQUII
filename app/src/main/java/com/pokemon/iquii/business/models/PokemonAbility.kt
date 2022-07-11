package com.pokemon.iquii.business.models

import java.io.Serializable

class PokemonAbility: Serializable {

    var ability: PokemonCommonObject = PokemonCommonObject()
    var isHidded: Boolean = false
    var slot: Int = 0

}
package com.pokemon.iquii.database.model

import java.io.Serializable

class PokemonAbilityDB: Serializable {

    var ability: PokemonCommonObjectDB = PokemonCommonObjectDB()
    var isHidded: Boolean = false
    var slot: Int = 0

}
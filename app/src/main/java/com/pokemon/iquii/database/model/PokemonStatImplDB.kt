package com.pokemon.iquii.database.model

import java.io.Serializable

class PokemonStatImplDB: Serializable {

    var baseStat: Int = 0
    var effort: Int = 0
    var stat: PokemonCommonObjectDB = PokemonCommonObjectDB()
}
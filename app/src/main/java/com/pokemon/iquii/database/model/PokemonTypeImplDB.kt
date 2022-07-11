package com.pokemon.iquii.database.model

import java.io.Serializable

class PokemonTypeImplDB: Serializable {

    var slot: Int = 0
    var type: PokemonCommonObjectDB = PokemonCommonObjectDB()
}
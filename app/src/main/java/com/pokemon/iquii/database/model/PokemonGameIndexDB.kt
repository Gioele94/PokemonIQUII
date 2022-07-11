package com.pokemon.iquii.database.model

import java.io.Serializable

class PokemonGameIndexDB: Serializable {

    val gameIndex: Int = 0
    val version: PokemonCommonObjectDB = PokemonCommonObjectDB()

}
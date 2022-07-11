package com.pokemon.iquii.database.model

import java.io.Serializable

class PokemonMoveDB : Serializable {

    var move: PokemonMoveDetailDB = PokemonMoveDetailDB()

}

class PokemonMoveDetailDB : PokemonCommonObjectDB(), Serializable {

    var versionGroupDetails: List<PokemonVersionGroupDetailDB> = emptyList()

}

class PokemonVersionGroupDetailDB : Serializable {

    var levelLearnedAt = 0
    var moveLearnMethod: PokemonCommonObjectDB = PokemonCommonObjectDB()
    var versionGroup: PokemonCommonObjectDB = PokemonCommonObjectDB()

}
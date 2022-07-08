package com.pokemon.iquii.business.models

class PokemonMove {

    val move: PokemonMoveDetail = PokemonMoveDetail()

}

class PokemonMoveDetail : PokemonCommonObject() {

    val versionGroupDetails: List<PokemonVersionGroupDetail> = emptyList()

}

class PokemonVersionGroupDetail {

    val levelLearnedAt = 0
    val moveLearnMethod: PokemonCommonObject = PokemonCommonObject()
    val versionGroup: PokemonCommonObject = PokemonCommonObject()

}
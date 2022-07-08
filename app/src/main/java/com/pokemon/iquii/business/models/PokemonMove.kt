package com.pokemon.iquii.business.models

class PokemonMove {

    var move: PokemonMoveDetail = PokemonMoveDetail()

}

class PokemonMoveDetail : PokemonCommonObject() {

    var versionGroupDetails: List<PokemonVersionGroupDetail> = emptyList()

}

class PokemonVersionGroupDetail {

    var levelLearnedAt = 0
    var moveLearnMethod: PokemonCommonObject = PokemonCommonObject()
    var versionGroup: PokemonCommonObject = PokemonCommonObject()

}
package com.pokemon.iquii.business.models

import java.io.Serializable

class PokemonMove: Serializable {

    var move: PokemonMoveDetail = PokemonMoveDetail()

}

class PokemonMoveDetail : PokemonCommonObject(), Serializable {

    var versionGroupDetails: List<PokemonVersionGroupDetail> = emptyList()

}

class PokemonVersionGroupDetail: Serializable {

    var levelLearnedAt = 0
    var moveLearnMethod: PokemonCommonObject = PokemonCommonObject()
    var versionGroup: PokemonCommonObject = PokemonCommonObject()

}
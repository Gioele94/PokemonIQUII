package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName

class PokemonMoveDTO {

    val move: PokemonMoveDetailDTO = PokemonMoveDetailDTO()

}

class PokemonMoveDetailDTO : PokemonCommonObjectDTO() {

    @SerializedName("version_group_details")
    val versionGroupDetails: List<PokemonVersionGroupDetailDTO> = emptyList()

}

class PokemonVersionGroupDetailDTO {

    @SerializedName("level_learned_at")
    val levelLearnedAt = 0

    @SerializedName("move_learn_method")
    val moveLearnMethod: PokemonCommonObjectDTO = PokemonCommonObjectDTO()

    @SerializedName("version_group")
    val versionGroup: PokemonCommonObjectDTO = PokemonCommonObjectDTO()
}
package com.pokemoniquiiSdk.services.pokemoncall.response.models

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.common.Response

open class PokemonDTO : Response() {

    @SerializedName("base_experience")
    val baseExperience: Int = 0

    @SerializedName("is_default")
    val isDefault = false

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String = ""

    val abilities: List<PokemonAbilityDTO> = emptyList()
    val forms: List<PokemonCommonObjectDTO> = emptyList()
    val height: Int = 0
    val weight: Int = 0
    val id: Int = 0
    val name: String = ""
    val order: Int = 0
    val moves: List<PokemonMoveDTO> = emptyList()
    val species: PokemonCommonObjectDTO = PokemonCommonObjectDTO()
    val sprites: PokemonSpriteDTO = PokemonSpriteDTO()
    val stats: List<PokemonStatImplDTO> = emptyList()
    val types: List<PokemonTypeImplDTO> = emptyList()

    //held_items		[0]
    //past_types		[0]

}
package com.pokemon.iquii.business.models

open class Pokemon {

    val baseExperience: Int = 0
    val isDefault = false
    val locationAreaEncounters: String = ""
    val abilities: List<PokemonAbility> = emptyList()
    val forms: List<PokemonCommonObject> = emptyList()
    val height: Int = 0
    val weight: Int = 0
    val id: Int = 0
    val name: String = ""
    val order: Int = 0
    val moves: List<PokemonMove> = emptyList()
    val species: List<PokemonCommonObject> = emptyList()
    val sprites: List<PokemonSpriteDTO> = emptyList()
    val stats: List<PokemonStatImpl> = emptyList()
    val types: List<PokemonTypeImpl> = emptyList()
}
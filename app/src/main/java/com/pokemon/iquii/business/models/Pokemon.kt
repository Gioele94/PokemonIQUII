package com.pokemon.iquii.business.models

import java.io.Serializable

open class Pokemon: Serializable {

    var baseExperience: Int = 0
    var isDefault = false
    var locationAreaEncounters: String = ""
    var abilities: List<PokemonAbility> = emptyList()
    var forms: List<PokemonCommonObject> = emptyList()
    var height: Int = 0
    var weight: Int = 0
    var id: Int = 0
    var name: String = ""
    var order: Int = 0
    var moves: List<PokemonMove> = emptyList()
    var species: PokemonCommonObject = PokemonCommonObject()
    var sprites: PokemonSprite = PokemonSprite()
    var stats: List<PokemonStatImpl> = emptyList()
    var types: List<PokemonTypeImpl> = emptyList()

}
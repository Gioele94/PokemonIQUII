package com.pokemon.iquii.database.model

import androidx.room.PrimaryKey
import java.io.Serializable

open class PokemonDB : Serializable {

    var baseExperience: Int = 0
    var isDefault = false
    var locationAreaEncounters: String = ""
    var abilities: List<PokemonAbilityDB> = emptyList()
    var forms: List<PokemonCommonObjectDB> = emptyList()
    var height: Int = 0
    var weight: Int = 0

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    var name: String = ""
    var order: Int = 0
    var moves: List<PokemonMoveDB> = emptyList()
    var species: PokemonCommonObjectDB = PokemonCommonObjectDB()
    var sprites: PokemonSpriteDB = PokemonSpriteDB()
    var stats: List<PokemonStatImplDB> = emptyList()
    var types: List<PokemonTypeImplDB> = emptyList()

}
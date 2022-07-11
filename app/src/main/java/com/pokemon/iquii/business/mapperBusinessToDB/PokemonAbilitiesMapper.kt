package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonAbility
import com.pokemon.iquii.database.model.PokemonAbilityDB

fun convertPokemonAbilitiesListModelToDB(list: List<PokemonAbility>): List<PokemonAbilityDB> {
    return list.map { convertPokemonAbilitiesModelToDB(it) }
}

fun convertPokemonAbilitiesModelToDB(pokemonAbility: PokemonAbility): PokemonAbilityDB {
    return PokemonAbilityDB().apply {
        this.ability = convertPokemonCommonObjectModelToDB(pokemonAbility.ability)
        this.slot = pokemonAbility.slot
        this.isHidded = pokemonAbility.isHidded
    }
}
package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonAbility
import com.pokemon.iquii.database.model.PokemonAbilityDB

fun convertPokemonAbilitiesListDBToModel(list: List<PokemonAbilityDB>): List<PokemonAbility> {
    return list.map { convertPokemonAbilitiesDBToModel(it) }
}

fun convertPokemonAbilitiesDBToModel(pokemonAbilityDB: PokemonAbilityDB): PokemonAbility {
    return PokemonAbility().apply {
        this.ability = convertPokemonCommonObjectDBToModel(pokemonAbilityDB.ability)
        this.slot = pokemonAbilityDB.slot
        this.isHidded = pokemonAbilityDB.isHidded
    }
}
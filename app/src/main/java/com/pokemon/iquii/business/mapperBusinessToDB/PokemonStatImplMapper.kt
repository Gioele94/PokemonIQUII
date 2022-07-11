package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemon.iquii.database.model.PokemonStatImplDB

fun convertPokemonStatImplListModelToDB(list: List<PokemonStatImpl>): List<PokemonStatImplDB> {
    return list.map { convertPokemonStatImplModelToDB(it) }
}

fun convertPokemonStatImplModelToDB(pokemonStatImpl: PokemonStatImpl): PokemonStatImplDB {
    return PokemonStatImplDB().apply {
        this.baseStat = pokemonStatImpl.baseStat
        this.effort = pokemonStatImpl.effort
        this.stat = convertPokemonCommonObjectModelToDB(pokemonStatImpl.stat)
    }
}
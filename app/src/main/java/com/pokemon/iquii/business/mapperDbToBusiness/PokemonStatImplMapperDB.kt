package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemon.iquii.database.model.PokemonStatImplDB

fun convertPokemonStatImplListDBToModel(list: List<PokemonStatImplDB>): List<PokemonStatImpl> {
    return list.map { convertPokemonStatImplDBToModel(it) }
}

fun convertPokemonStatImplDBToModel(pokemonStatImplDB: PokemonStatImplDB): PokemonStatImpl {
    return PokemonStatImpl().apply {
        this.baseStat = pokemonStatImplDB.baseStat
        this.effort = pokemonStatImplDB.effort
        this.stat = convertPokemonCommonObjectDBToModel(pokemonStatImplDB.stat)
    }
}
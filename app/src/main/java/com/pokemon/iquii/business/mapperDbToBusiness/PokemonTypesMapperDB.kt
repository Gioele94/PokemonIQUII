package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonTypeImpl
import com.pokemon.iquii.database.model.PokemonTypeImplDB

fun convertPokemonTypesImplListDBToModel(list: List<PokemonTypeImplDB>): List<PokemonTypeImpl> {
    return list.map { convertPokemonTypeImplDBToModel(it) }
}

fun convertPokemonTypeImplDBToModel(pokemonTypeImplDB: PokemonTypeImplDB): PokemonTypeImpl {
    return PokemonTypeImpl().apply {
        this.type = convertPokemonCommonObjectDBToModel(pokemonTypeImplDB.type)
        this.slot = pokemonTypeImplDB.slot
    }
}
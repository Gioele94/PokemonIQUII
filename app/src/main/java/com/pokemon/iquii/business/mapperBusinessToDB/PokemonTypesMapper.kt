package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonTypeImpl
import com.pokemon.iquii.database.model.PokemonTypeImplDB

fun convertPokemonTypesImplListModelToDB(list: List<PokemonTypeImpl>): List<PokemonTypeImplDB> {
    return list.map { convertPokemonTypeImplModelToDB(it) }
}

fun convertPokemonTypeImplModelToDB(pokemonTypeImplDTO: PokemonTypeImpl): PokemonTypeImplDB {
    return PokemonTypeImplDB().apply {
        this.type = convertPokemonCommonObjectModelToDB(pokemonTypeImplDTO.type)
        this.slot = pokemonTypeImplDTO.slot
    }
}
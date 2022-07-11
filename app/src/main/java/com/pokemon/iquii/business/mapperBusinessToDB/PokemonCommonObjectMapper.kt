package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonCommonObject
import com.pokemon.iquii.database.model.PokemonCommonObjectDB

fun convertPokemonCommonObjectListModelToDB(list: List<PokemonCommonObject>): List<PokemonCommonObjectDB> {
    return list.map { convertPokemonCommonObjectModelToDB(it) }
}

fun convertPokemonCommonObjectModelToDB(pokemonCommonObject: PokemonCommonObject): PokemonCommonObjectDB {
    return PokemonCommonObjectDB().apply {
        this.name = pokemonCommonObject.name
        this.url = pokemonCommonObject.url
    }
}
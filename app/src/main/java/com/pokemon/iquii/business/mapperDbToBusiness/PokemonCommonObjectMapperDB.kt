package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonCommonObject
import com.pokemon.iquii.database.model.PokemonCommonObjectDB

fun convertPokemonCommonObjectListDBToModel(list: List<PokemonCommonObjectDB>): List<PokemonCommonObject> {
    return list.map { convertPokemonCommonObjectDBToModel(it) }
}

fun convertPokemonCommonObjectDBToModel(pokemonCommonObjectDB: PokemonCommonObjectDB): PokemonCommonObject {
    return PokemonCommonObject().apply {
        this.name = pokemonCommonObjectDB.name
        this.url = pokemonCommonObjectDB.url
    }
}
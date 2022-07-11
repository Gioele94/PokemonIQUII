package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonMove
import com.pokemon.iquii.business.models.PokemonMoveDetail
import com.pokemon.iquii.business.models.PokemonVersionGroupDetail
import com.pokemon.iquii.database.model.PokemonMoveDB
import com.pokemon.iquii.database.model.PokemonMoveDetailDB
import com.pokemon.iquii.database.model.PokemonVersionGroupDetailDB

fun convertPokemonMoveListDBToModel(list: List<PokemonMoveDB>): List<PokemonMove> {
    return list.map { convertPokemonMoveDBToModel(it) }
}

fun convertPokemonMoveDBToModel(pokemonMoveDB: PokemonMoveDB): PokemonMove {
    return PokemonMove().apply {
        this.move = convertPokemonMoveDetailDBToModel(pokemonMoveDB.move)
    }
}

fun convertPokemonMoveDetailDBToModel(pokemonMoveDetailDB: PokemonMoveDetailDB): PokemonMoveDetail {
    return PokemonMoveDetail().apply {
        this.name = pokemonMoveDetailDB.name
        this.url = pokemonMoveDetailDB.url
        this.versionGroupDetails = convertPokemonVersionGroupDetailListDBToModel(pokemonMoveDetailDB.versionGroupDetails)
    }
}

fun convertPokemonVersionGroupDetailListDBToModel(list: List<PokemonVersionGroupDetailDB>): List<PokemonVersionGroupDetail> {
    return list.map { convertPokemonVersionGroupDetailDBToModel(it) }
}

fun convertPokemonVersionGroupDetailDBToModel(pokemonVersionGroupDetailDB: PokemonVersionGroupDetailDB): PokemonVersionGroupDetail {
    return PokemonVersionGroupDetail().apply {
        this.versionGroup = convertPokemonCommonObjectDBToModel(pokemonVersionGroupDetailDB.versionGroup)
        this.levelLearnedAt = pokemonVersionGroupDetailDB.levelLearnedAt
        this.moveLearnMethod = convertPokemonCommonObjectDBToModel(pokemonVersionGroupDetailDB.moveLearnMethod)
    }
}
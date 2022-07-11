package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonMove
import com.pokemon.iquii.business.models.PokemonMoveDetail
import com.pokemon.iquii.business.models.PokemonVersionGroupDetail
import com.pokemon.iquii.database.model.PokemonMoveDB
import com.pokemon.iquii.database.model.PokemonMoveDetailDB
import com.pokemon.iquii.database.model.PokemonVersionGroupDetailDB

fun convertPokemonMoveListModelToDB(list: List<PokemonMove>): List<PokemonMoveDB> {
    return list.map { convertPokemonMoveModelToDB(it) }
}

fun convertPokemonMoveModelToDB(pokemonMoveDto: PokemonMove): PokemonMoveDB {
    return PokemonMoveDB().apply {
        this.move = convertPokemonMoveDetailModelToDB(pokemonMoveDto.move)
    }
}

fun convertPokemonMoveDetailModelToDB(pokemonMoveDetailDto: PokemonMoveDetail): PokemonMoveDetailDB {
    return PokemonMoveDetailDB().apply {
        this.name = pokemonMoveDetailDto.name
        this.url = pokemonMoveDetailDto.url
        this.versionGroupDetails =
            convertPokemonVersionGroupDetailListModelToDB(pokemonMoveDetailDto.versionGroupDetails)
    }
}

fun convertPokemonVersionGroupDetailListModelToDB(list: List<PokemonVersionGroupDetail>): List<PokemonVersionGroupDetailDB> {
    return list.map { convertPokemonVersionGroupDetailModelToDB(it) }
}

fun convertPokemonVersionGroupDetailModelToDB(pokemonVersionGroupDetail: PokemonVersionGroupDetail): PokemonVersionGroupDetailDB {
    return PokemonVersionGroupDetailDB().apply {
        this.versionGroup =
            convertPokemonCommonObjectModelToDB(pokemonVersionGroupDetail.versionGroup)
        this.levelLearnedAt = pokemonVersionGroupDetail.levelLearnedAt
        this.moveLearnMethod =
            convertPokemonCommonObjectModelToDB(pokemonVersionGroupDetail.moveLearnMethod)
    }
}
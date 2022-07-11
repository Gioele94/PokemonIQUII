package com.pokemon.iquii.business.mapperDtoToBusiness

import com.pokemon.iquii.business.models.PokemonMove
import com.pokemon.iquii.business.models.PokemonMoveDetail
import com.pokemon.iquii.business.models.PokemonVersionGroupDetail
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonMoveDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonMoveDetailDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonVersionGroupDetailDTO

fun convertPokemonMoveListDtoToModel(list: List<PokemonMoveDTO>): List<PokemonMove> {
    return list.map { convertPokemonMoveDtoToModel(it) }
}

fun convertPokemonMoveDtoToModel(pokemonMoveDto: PokemonMoveDTO): PokemonMove {
    return PokemonMove().apply {
        this.move = convertPokemonMoveDetailDtoToModel(pokemonMoveDto.move)
    }
}

fun convertPokemonMoveDetailDtoToModel(pokemonMoveDetailDto: PokemonMoveDetailDTO): PokemonMoveDetail {
    return PokemonMoveDetail().apply {
        this.name = pokemonMoveDetailDto.name
        this.url = pokemonMoveDetailDto.url
        this.versionGroupDetails = convertPokemonVersionGroupDetailListDtoToModel(pokemonMoveDetailDto.versionGroupDetails)
    }
}

fun convertPokemonVersionGroupDetailListDtoToModel(list: List<PokemonVersionGroupDetailDTO>): List<PokemonVersionGroupDetail> {
    return list.map { convertPokemonVersionGroupDetailDtoToModel(it) }
}

fun convertPokemonVersionGroupDetailDtoToModel(pokemonVersionGroupDetailDTO: PokemonVersionGroupDetailDTO): PokemonVersionGroupDetail {
    return PokemonVersionGroupDetail().apply {
        this.versionGroup = convertPokemonCommonObjectDtoToModel(pokemonVersionGroupDetailDTO.versionGroup)
        this.levelLearnedAt = pokemonVersionGroupDetailDTO.levelLearnedAt
        this.moveLearnMethod = convertPokemonCommonObjectDtoToModel(pokemonVersionGroupDetailDTO.moveLearnMethod)
    }
}
package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.PokemonCommonObject
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonObjectDTO

fun convertPokemonCommonObjectListDtoToModel(list: List<PokemonCommonObjectDTO>): List<PokemonCommonObject> {
    return list.map { convertPokemonCommonObjectDtoToModel(it) }
}

fun convertPokemonCommonObjectDtoToModel(pokemonCommonObjectDTO: PokemonCommonObjectDTO): PokemonCommonObject {
    return PokemonCommonObject().apply {
        this.name = pokemonCommonObjectDTO.name
        this.url = pokemonCommonObjectDTO.url
    }
}
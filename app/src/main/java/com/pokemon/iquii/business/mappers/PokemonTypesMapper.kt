package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.PokemonTypeImpl
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonTypeImplDTO

fun convertPokemonTypesImplListDtoToModel(list: List<PokemonTypeImplDTO>): List<PokemonTypeImpl> {
    return list.map { convertPokemonTypeImplDtoToModel(it) }
}

fun convertPokemonTypeImplDtoToModel(pokemonTypeImplDTO: PokemonTypeImplDTO): PokemonTypeImpl {
    return PokemonTypeImpl().apply {
        this.type = convertPokemonCommonObjectDtoToModel(pokemonTypeImplDTO.type)
        this.slot = pokemonTypeImplDTO.slot
    }
}
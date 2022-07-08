package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonStatImplDTO

fun convertPokemonStatImplListDtoToModel(list: List<PokemonStatImplDTO>): List<PokemonStatImpl> {
    return list.map { convertPokemonStatImplDtoToModel(it) }
}

fun convertPokemonStatImplDtoToModel(pokemonStatImplDTO: PokemonStatImplDTO): PokemonStatImpl {
    return PokemonStatImpl().apply {
        this.baseStat = pokemonStatImplDTO.baseStat
        this.effort = pokemonStatImplDTO.effort
        this.stat = convertPokemonCommonObjectDtoToModel(pokemonStatImplDTO.stat)
    }
}
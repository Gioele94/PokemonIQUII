package com.pokemon.iquii.business.mapperDtoToBusiness

import com.pokemon.iquii.business.models.PokemonAbility
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonAbilityDTO

fun convertPokemonAbilitiesListDtoToModel(list: List<PokemonAbilityDTO>): List<PokemonAbility> {
    return list.map { convertPokemonAbilitiesDtoToModel(it) }
}

fun convertPokemonAbilitiesDtoToModel(pokemonAbilityDTO: PokemonAbilityDTO):  PokemonAbility{
    return PokemonAbility().apply {
        this.ability = convertPokemonCommonObjectDtoToModel(pokemonAbilityDTO.ability)
        this.slot = pokemonAbilityDTO.slot
        this.isHidded = pokemonAbilityDTO.isHidded
    }
}
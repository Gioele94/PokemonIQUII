package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.Pokemon
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonDTO

fun convertPokemonDtoToModel(pokemonDTO: PokemonDTO): Pokemon {
    return Pokemon().apply {
        this.baseExperience = pokemonDTO.baseExperience
        this.isDefault = pokemonDTO.isDefault
        this.locationAreaEncounters = pokemonDTO.locationAreaEncounters
        this.height = pokemonDTO.height
        this.weight = pokemonDTO.weight
        this.id = pokemonDTO.id
        this.name = pokemonDTO.name
        this.order = pokemonDTO.order
        this.moves = convertPokemonMoveListDtoToModel(pokemonDTO.moves)
        this.abilities = convertPokemonAbilitiesListDtoToModel(pokemonDTO.abilities)
        this.forms = convertPokemonCommonObjectListDtoToModel(pokemonDTO.forms)
        this.species = convertPokemonCommonObjectDtoToModel(pokemonDTO.species)
        this.sprites = convertPokemonSpriteDtoToModel(pokemonDTO.sprites)
        this.stats = convertPokemonStatImplListDtoToModel(pokemonDTO.stats)
        this.types = convertPokemonTypesImplListDtoToModel(pokemonDTO.types)

    }

}

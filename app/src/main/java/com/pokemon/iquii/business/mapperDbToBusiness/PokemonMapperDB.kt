package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.database.model.PokemonDB

fun convertPokemonDBToModel(pokemonDB: PokemonDB): Pokemon {
    return Pokemon().apply {
        this.baseExperience = pokemonDB.baseExperience
        this.isDefault = pokemonDB.isDefault
        this.locationAreaEncounters = pokemonDB.locationAreaEncounters
        this.height = pokemonDB.height
        this.weight = pokemonDB.weight
        this.id = pokemonDB.id
        this.name = pokemonDB.name
        this.order = pokemonDB.order
        this.moves = convertPokemonMoveListDBToModel(pokemonDB.moves)
        this.abilities = convertPokemonAbilitiesListDBToModel(pokemonDB.abilities)
        this.forms = convertPokemonCommonObjectListDBToModel(pokemonDB.forms)
        this.species = convertPokemonCommonObjectDBToModel(pokemonDB.species)
        this.sprites = convertPokemonSpriteDBToModel(pokemonDB.sprites)
        this.stats = convertPokemonStatImplListDBToModel(pokemonDB.stats)
        this.types = convertPokemonTypesImplListDBToModel(pokemonDB.types)

    }

}

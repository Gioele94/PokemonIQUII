package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.database.model.PokemonDB
import com.pokemon.iquii.database.model.PokemonTableDB
import com.pokemon.iquii.database.model.PokemonTableFavoriteDB

fun convertPokemonModelListToDB(list: List<Pokemon>): List<PokemonTableDB> {
    return list.map { convertPokemonModelToDB(it) }
}

fun convertPokemonModelListToDB(list: List<Pokemon>, isFavoriteDB: Boolean): List<PokemonTableFavoriteDB> {
    return list.map { convertPokemonModelToDB(it, isFavoriteDB) }
}

fun convertPokemonModelToDB(pokemon: Pokemon): PokemonTableDB {
    return PokemonTableDB().apply {
        this.baseExperience = pokemon.baseExperience
        this.isDefault = pokemon.isDefault
        this.locationAreaEncounters = pokemon.locationAreaEncounters
        this.height = pokemon.height
        this.weight = pokemon.weight
        this.id = pokemon.id
        this.name = pokemon.name
        this.order = pokemon.order
        this.moves = convertPokemonMoveListModelToDB(pokemon.moves)
        this.abilities = convertPokemonAbilitiesListModelToDB(pokemon.abilities)
        this.forms = convertPokemonCommonObjectListModelToDB(pokemon.forms)
        this.species = convertPokemonCommonObjectModelToDB(pokemon.species)
        this.sprites = convertPokemonSpriteModelToDB(pokemon.sprites)
        this.stats = convertPokemonStatImplListModelToDB(pokemon.stats)
        this.types = convertPokemonTypesImplListModelToDB(pokemon.types)

    }
}

fun convertPokemonModelToDB(pokemon: Pokemon, isFavoriteDB: Boolean): PokemonTableFavoriteDB {
    return PokemonTableFavoriteDB().apply {
        this.baseExperience = pokemon.baseExperience
        this.isDefault = pokemon.isDefault
        this.locationAreaEncounters = pokemon.locationAreaEncounters
        this.height = pokemon.height
        this.weight = pokemon.weight
        this.id = pokemon.id
        this.name = pokemon.name
        this.order = pokemon.order
        this.moves = convertPokemonMoveListModelToDB(pokemon.moves)
        this.abilities = convertPokemonAbilitiesListModelToDB(pokemon.abilities)
        this.forms = convertPokemonCommonObjectListModelToDB(pokemon.forms)
        this.species = convertPokemonCommonObjectModelToDB(pokemon.species)
        this.sprites = convertPokemonSpriteModelToDB(pokemon.sprites)
        this.stats = convertPokemonStatImplListModelToDB(pokemon.stats)
        this.types = convertPokemonTypesImplListModelToDB(pokemon.types)

    }

}

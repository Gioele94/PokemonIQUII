package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonCommonSprite
import com.pokemon.iquii.business.models.PokemonSprite
import com.pokemon.iquii.business.models.PokemonSpriteOther
import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import com.pokemon.iquii.database.model.PokemonSpriteDB
import com.pokemon.iquii.database.model.PokemonSpriteOtherDB

fun convertPokemonCommonSpriteDBToModel(pokemonCommonSpriteDB: PokemonCommonSpriteDB): PokemonCommonSprite {
    return PokemonCommonSprite().apply {
        this.backDefault = pokemonCommonSpriteDB.backDefault
        this.backFemale = pokemonCommonSpriteDB.backFemale
        this.backShiny = pokemonCommonSpriteDB.backShiny
        this.backShinyFemale = pokemonCommonSpriteDB.backShinyFemale
        this.frontDefault = pokemonCommonSpriteDB.frontDefault
        this.frontFemale = pokemonCommonSpriteDB.frontFemale
        this.frontShiny = pokemonCommonSpriteDB.frontShiny
        this.frontShinyFemale = pokemonCommonSpriteDB.frontShinyFemale
        this.backShinyTransparent = pokemonCommonSpriteDB.backShinyTransparent
        this.backTransparent = pokemonCommonSpriteDB.backTransparent
        this.frontShinyTransparent = pokemonCommonSpriteDB.frontShinyTransparent
        this.frontTransparent = pokemonCommonSpriteDB.frontTransparent
    }
}

fun convertPokemonSpriteDBToModel(pokemonSpriteDB: PokemonSpriteDB): PokemonSprite {
    return PokemonSprite().apply {
        convertPokemonCommonSpriteDBToModel(pokemonSpriteDB)
        this.other = convertPokemonSpriteOtherDBToModel(pokemonSpriteDB.other)
    }
}


fun convertPokemonSpriteOtherDBToModel(pokemonSpriteOtherDB: PokemonSpriteOtherDB): PokemonSpriteOther {
    return PokemonSpriteOther().apply {
        this.dreamWorld = convertPokemonCommonSpriteDBToModel(pokemonSpriteOtherDB.dreamWorld)
        this.home = convertPokemonCommonSpriteDBToModel(pokemonSpriteOtherDB.home)
        this.officialArtwork =
            convertPokemonCommonSpriteDBToModel(pokemonSpriteOtherDB.officialArtwork)
        this.versions = convertPokemonSpriteGenerationDBToModel(pokemonSpriteOtherDB.versions)
    }
}

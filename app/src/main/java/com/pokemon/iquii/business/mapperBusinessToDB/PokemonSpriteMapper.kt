package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonCommonSprite
import com.pokemon.iquii.business.models.PokemonSprite
import com.pokemon.iquii.business.models.PokemonSpriteOther
import com.pokemon.iquii.database.model.PokemonCommonSpriteDB
import com.pokemon.iquii.database.model.PokemonSpriteDB
import com.pokemon.iquii.database.model.PokemonSpriteOtherDB

fun convertPokemonCommonSpriteModelToDB(pokemonCommonSprite: PokemonCommonSprite): PokemonCommonSpriteDB {
    return PokemonCommonSpriteDB().apply {
        this.backDefault = pokemonCommonSprite.backDefault
        this.backFemale = pokemonCommonSprite.backFemale
        this.backShiny = pokemonCommonSprite.backShiny
        this.backShinyFemale = pokemonCommonSprite.backShinyFemale
        this.frontDefault = pokemonCommonSprite.frontDefault
        this.frontFemale = pokemonCommonSprite.frontFemale
        this.frontShiny = pokemonCommonSprite.frontShiny
        this.frontShinyFemale = pokemonCommonSprite.frontShinyFemale
        this.backShinyTransparent = pokemonCommonSprite.backShinyTransparent
        this.backTransparent = pokemonCommonSprite.backTransparent
        this.frontShinyTransparent = pokemonCommonSprite.frontShinyTransparent
        this.frontTransparent = pokemonCommonSprite.frontTransparent
    }
}

fun convertPokemonSpriteModelToDB(pokemonSprite: PokemonSprite): PokemonSpriteDB {
    return PokemonSpriteDB().apply {
        convertPokemonCommonSpriteModelToDB(pokemonSprite)
        this.other = convertPokemonSpriteOtherModelToDB(pokemonSprite.other)
    }
}


fun convertPokemonSpriteOtherModelToDB(pokemonSpriteOther: PokemonSpriteOther): PokemonSpriteOtherDB {
    return PokemonSpriteOtherDB().apply {
        this.dreamWorld = convertPokemonCommonSpriteModelToDB(pokemonSpriteOther.dreamWorld)
        this.home = convertPokemonCommonSpriteModelToDB(pokemonSpriteOther.home)
        this.officialArtwork =
            convertPokemonCommonSpriteModelToDB(pokemonSpriteOther.officialArtwork)
        this.versions = convertPokemonSpriteGenerationModelToDB(pokemonSpriteOther.versions)
    }
}

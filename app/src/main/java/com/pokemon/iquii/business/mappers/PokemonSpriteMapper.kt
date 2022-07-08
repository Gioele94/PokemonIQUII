package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.PokemonCommonSprite
import com.pokemon.iquii.business.models.PokemonSprite
import com.pokemon.iquii.business.models.PokemonSpriteOther
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonSpriteDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonSpriteOtherDTO

fun convertPokemonCommonSpriteDtoToModel(pokemonCommonSpriteDto: PokemonCommonSpriteDTO): PokemonCommonSprite {
    return PokemonCommonSprite().apply {
        this.backDefault = pokemonCommonSpriteDto.backDefault
        this.backFemale = pokemonCommonSpriteDto.backFemale
        this.backShiny = pokemonCommonSpriteDto.backShiny
        this.backShinyFemale = pokemonCommonSpriteDto.backShinyFemale
        this.frontDefault = pokemonCommonSpriteDto.frontDefault
        this.frontFemale = pokemonCommonSpriteDto.frontFemale
        this.frontShiny = pokemonCommonSpriteDto.frontShiny
        this.frontShinyFemale = pokemonCommonSpriteDto.frontShinyFemale
        this.backShinyTransparent = pokemonCommonSpriteDto.backShinyTransparent
        this.backTransparent = pokemonCommonSpriteDto.backTransparent
        this.frontShinyTransparent = pokemonCommonSpriteDto.frontShinyTransparent
        this.frontTransparent = pokemonCommonSpriteDto.frontTransparent
    }
}

fun convertPokemonSpriteDtoToModel(pokemonSpriteDto: PokemonSpriteDTO): PokemonSprite {
    return PokemonSprite().apply {
       convertPokemonCommonSpriteDtoToModel(pokemonSpriteDto)
        this.other = convertPokemonSpriteOtherDtoToModel(pokemonSpriteDto.other)
    }
}


fun convertPokemonSpriteOtherDtoToModel(pokemonSpriteOtherDTO: PokemonSpriteOtherDTO): PokemonSpriteOther {
    return PokemonSpriteOther().apply {
        this.dreamWorld = convertPokemonCommonSpriteDtoToModel(pokemonSpriteOtherDTO.dreamWorld)
        this.home = convertPokemonCommonSpriteDtoToModel(pokemonSpriteOtherDTO.home)
        this.officialArtwork = convertPokemonCommonSpriteDtoToModel(pokemonSpriteOtherDTO.officialArtwork)
        this.versions = convertPokemonSpriteGenerationDtoToModel(pokemonSpriteOtherDTO.versions)
    }
}

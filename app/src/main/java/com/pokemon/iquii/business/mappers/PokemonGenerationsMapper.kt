package com.pokemon.iquii.business.mappers

import com.pokemon.iquii.business.models.PokemonSpriteGeneration
import com.pokemon.iquii.business.models.generation.*
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonSpriteGenerationDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.generation.*

fun convertPokemonSpriteGenerationDtoToModel(pokemonSpriteGenerationDto: PokemonSpriteGenerationDTO): PokemonSpriteGeneration {
    return PokemonSpriteGeneration().apply {
        this.generationI = convertPokemonIGenerationDtoToModel(pokemonSpriteGenerationDto.generationI)
        this.generationII = convertPokemonIIGenerationDtoToModel(pokemonSpriteGenerationDto.generationII)
        this.generationIII = convertPokemonIIIGenerationDtoToModel(pokemonSpriteGenerationDto.generationIII)
        this.generationIV = convertPokemonIVGenerationDtoToModel(pokemonSpriteGenerationDto.generationIV)
        this.generationV = convertPokemonVGenerationDtoToModel(pokemonSpriteGenerationDto.generationV)
        this.generationVI = convertPokemonVIGenerationDtoToModel(pokemonSpriteGenerationDto.generationVI)
        this.generationVII = convertPokemonVIIGenerationDtoToModel(pokemonSpriteGenerationDto.generationVII)
        this.generationVIII = convertPokemonVIIIGenerationDtoToModel(pokemonSpriteGenerationDto.generationVIII)

    }
}

fun convertPokemonIGenerationDtoToModel(pokemonIGenerationDTO: PokemonIGenerationDTO?): PokemonIGeneration? {
    pokemonIGenerationDTO?.let {
        return PokemonIGeneration().apply {
            this.redBlue = convertPokemonCommonSpriteDtoToModel(pokemonIGenerationDTO.redBlue)
            this.yellow = convertPokemonCommonSpriteDtoToModel(pokemonIGenerationDTO.yellow)
        }
    }
    return null
}

fun convertPokemonIIGenerationDtoToModel(pokemonIIGenerationDTO: PokemonIIGenerationDTO?): PokemonIIGeneration? {
    pokemonIIGenerationDTO?.let {
        return PokemonIIGeneration().apply {
            this.icons = pokemonIIGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.crystal = convertPokemonCommonSpriteDtoToModel(pokemonIIGenerationDTO.crystal)
            this.gold = convertPokemonCommonSpriteDtoToModel(pokemonIIGenerationDTO.gold)
            this.silver = convertPokemonCommonSpriteDtoToModel(pokemonIIGenerationDTO.silver)
        }
    }
    return null
}


fun convertPokemonIIIGenerationDtoToModel(pokemonIIIGenerationDTO: PokemonIIIGenerationDTO?): PokemonIIIGeneration? {
    pokemonIIIGenerationDTO?.let {
        return PokemonIIIGeneration().apply {
            this.icons = pokemonIIIGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.emerald = convertPokemonCommonSpriteDtoToModel(pokemonIIIGenerationDTO.emerald)
            this.rubySapphire = convertPokemonCommonSpriteDtoToModel(pokemonIIIGenerationDTO.rubySapphire)
            this.fireredLeafgreen = convertPokemonCommonSpriteDtoToModel(pokemonIIIGenerationDTO.fireredLeafgreen)
        }
    }
    return null
}

fun convertPokemonIVGenerationDtoToModel(pokemonIVGenerationDTO: PokemonIVGenerationDTO?): PokemonIVGeneration? {
    pokemonIVGenerationDTO?.let {
        return PokemonIVGeneration().apply {
            this.icons = pokemonIVGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.diamondPearl = convertPokemonCommonSpriteDtoToModel(pokemonIVGenerationDTO.diamondPearl)
            this.platinum = convertPokemonCommonSpriteDtoToModel(pokemonIVGenerationDTO.platinum)
            this.heartgoldSoulsilver = convertPokemonCommonSpriteDtoToModel(pokemonIVGenerationDTO.heartgoldSoulsilver)
        }
    }
    return null
}


fun convertPokemonVGenerationDtoToModel(pokemonVGenerationDTO: PokemonVGenerationDTO?): PokemonVGeneration? {
    pokemonVGenerationDTO?.let {
        return PokemonVGeneration().apply {
            this.icons = pokemonVGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.blackWhite = convertPokemonCommonSpriteDtoToModel(pokemonVGenerationDTO.blackWhite)
        }
    }
    return null
}

fun convertPokemonVIGenerationDtoToModel(pokemonVIGenerationDTO: PokemonVIGenerationDTO?): PokemonVIGeneration? {
    pokemonVIGenerationDTO?.let {
        return PokemonVIGeneration().apply {
            this.icons = pokemonVIGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.omegarubyAlphasapphire = convertPokemonCommonSpriteDtoToModel(pokemonVIGenerationDTO.omegarubyAlphasapphire)
            this.xy = convertPokemonCommonSpriteDtoToModel(pokemonVIGenerationDTO.xy)
        }
    }
    return null
}

fun convertPokemonVIIGenerationDtoToModel(pokemonVIIGenerationDTO: PokemonVIIGenerationDTO?): PokemonVIIGeneration? {
    pokemonVIIGenerationDTO?.let {
        return PokemonVIIGeneration().apply {
            this.icons = pokemonVIIGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
            this.ultraSunUltraMoon = convertPokemonCommonSpriteDtoToModel(pokemonVIIGenerationDTO.ultraSunUltraMoon)
        }
    }
    return null
}

fun convertPokemonVIIIGenerationDtoToModel(pokemonVIIIGenerationDTO: PokemonVIIIGenerationDTO?): PokemonVIIIGeneration? {
    pokemonVIIIGenerationDTO?.let {
        return PokemonVIIIGeneration().apply {
            this.icons = pokemonVIIIGenerationDTO.icons?.let {
                convertPokemonCommonSpriteDtoToModel(it)
            }
        }
    }
    return null
}
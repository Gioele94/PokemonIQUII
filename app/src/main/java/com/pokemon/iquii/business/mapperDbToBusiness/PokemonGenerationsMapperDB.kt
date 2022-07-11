package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.models.PokemonSpriteGeneration
import com.pokemon.iquii.business.models.generation.*
import com.pokemon.iquii.database.model.PokemonSpriteGenerationDB
import com.pokemon.iquii.database.model.generation.*

fun convertPokemonSpriteGenerationDBToModel(pokemonSpriteGenerationDB: PokemonSpriteGenerationDB): PokemonSpriteGeneration {
    return PokemonSpriteGeneration().apply {
        this.generationI = convertPokemonIGenerationDBToModel(pokemonSpriteGenerationDB.generationI)
        this.generationII =
            convertPokemonIIGenerationDBToModel(pokemonSpriteGenerationDB.generationII)
        this.generationIII =
            convertPokemonIIIGenerationDBToModel(pokemonSpriteGenerationDB.generationIII)
        this.generationIV =
            convertPokemonIVGenerationDBToModel(pokemonSpriteGenerationDB.generationIV)
        this.generationV = convertPokemonVGenerationDBToModel(pokemonSpriteGenerationDB.generationV)
        this.generationVI =
            convertPokemonVIGenerationDBToModel(pokemonSpriteGenerationDB.generationVI)
        this.generationVII =
            convertPokemonVIIGenerationDBToModel(pokemonSpriteGenerationDB.generationVII)
        this.generationVIII =
            convertPokemonVIIIGenerationDBToModel(pokemonSpriteGenerationDB.generationVIII)

    }
}

fun convertPokemonIGenerationDBToModel(pokemonIGenerationDB: PokemonIGenerationDB?): PokemonIGeneration? {
    pokemonIGenerationDB?.let {
        return PokemonIGeneration().apply {
            this.redBlue = convertPokemonCommonSpriteDBToModel(pokemonIGenerationDB.redBlue)
            this.yellow = convertPokemonCommonSpriteDBToModel(pokemonIGenerationDB.yellow)
        }
    }
    return null
}

fun convertPokemonIIGenerationDBToModel(pokemonIIGenerationDB: PokemonIIGenerationDB?): PokemonIIGeneration? {
    pokemonIIGenerationDB?.let {
        return PokemonIIGeneration().apply {
            this.icons = pokemonIIGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.crystal = convertPokemonCommonSpriteDBToModel(pokemonIIGenerationDB.crystal)
            this.gold = convertPokemonCommonSpriteDBToModel(pokemonIIGenerationDB.gold)
            this.silver = convertPokemonCommonSpriteDBToModel(pokemonIIGenerationDB.silver)
        }
    }
    return null
}


fun convertPokemonIIIGenerationDBToModel(pokemonIIIGenerationDB: PokemonIIIGenerationDB?): PokemonIIIGeneration? {
    pokemonIIIGenerationDB?.let {
        return PokemonIIIGeneration().apply {
            this.icons = pokemonIIIGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.emerald = convertPokemonCommonSpriteDBToModel(pokemonIIIGenerationDB.emerald)
            this.rubySapphire =
                convertPokemonCommonSpriteDBToModel(pokemonIIIGenerationDB.rubySapphire)
            this.fireredLeafgreen =
                convertPokemonCommonSpriteDBToModel(pokemonIIIGenerationDB.fireredLeafgreen)
        }
    }
    return null
}

fun convertPokemonIVGenerationDBToModel(pokemonIVGenerationDB: PokemonIVGenerationDB?): PokemonIVGeneration? {
    pokemonIVGenerationDB?.let {
        return PokemonIVGeneration().apply {
            this.icons = pokemonIVGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.diamondPearl =
                convertPokemonCommonSpriteDBToModel(pokemonIVGenerationDB.diamondPearl)
            this.platinum = convertPokemonCommonSpriteDBToModel(pokemonIVGenerationDB.platinum)
            this.heartgoldSoulsilver =
                convertPokemonCommonSpriteDBToModel(pokemonIVGenerationDB.heartgoldSoulsilver)
        }
    }
    return null
}


fun convertPokemonVGenerationDBToModel(pokemonVGenerationDB: PokemonVGenerationDB?): PokemonVGeneration? {
    pokemonVGenerationDB?.let {
        return PokemonVGeneration().apply {
            this.icons = pokemonVGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.blackWhite = convertPokemonCommonSpriteDBToModel(pokemonVGenerationDB.blackWhite)
        }
    }
    return null
}

fun convertPokemonVIGenerationDBToModel(pokemonVIGenerationDB: PokemonVIGenerationDB?): PokemonVIGeneration? {
    pokemonVIGenerationDB?.let {
        return PokemonVIGeneration().apply {
            this.icons = pokemonVIGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.omegarubyAlphasapphire =
                convertPokemonCommonSpriteDBToModel(pokemonVIGenerationDB.omegarubyAlphasapphire)
            this.xy = convertPokemonCommonSpriteDBToModel(pokemonVIGenerationDB.xy)
        }
    }
    return null
}

fun convertPokemonVIIGenerationDBToModel(pokemonVIIGenerationDB: PokemonVIIGenerationDB?): PokemonVIIGeneration? {
    pokemonVIIGenerationDB?.let {
        return PokemonVIIGeneration().apply {
            this.icons = pokemonVIIGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
            this.ultraSunUltraMoon =
                convertPokemonCommonSpriteDBToModel(pokemonVIIGenerationDB.ultraSunUltraMoon)
        }
    }
    return null
}

fun convertPokemonVIIIGenerationDBToModel(pokemonVIIIGenerationDB: PokemonVIIIGenerationDB?): PokemonVIIIGeneration? {
    pokemonVIIIGenerationDB?.let {
        return PokemonVIIIGeneration().apply {
            this.icons = pokemonVIIIGenerationDB.icons?.let {
                convertPokemonCommonSpriteDBToModel(it)
            }
        }
    }
    return null
}
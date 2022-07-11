package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.models.PokemonSpriteGeneration
import com.pokemon.iquii.business.models.generation.*
import com.pokemon.iquii.database.model.PokemonSpriteGenerationDB
import com.pokemon.iquii.database.model.generation.*

fun convertPokemonSpriteGenerationModelToDB(pokemonSpriteGeneration: PokemonSpriteGeneration): PokemonSpriteGenerationDB {
    return PokemonSpriteGenerationDB().apply {
        this.generationI = convertPokemonIGenerationModelToDB(pokemonSpriteGeneration.generationI)
        this.generationII =
            convertPokemonIIGenerationModelToDB(pokemonSpriteGeneration.generationII)
        this.generationIII =
            convertPokemonIIIGenerationModelToDB(pokemonSpriteGeneration.generationIII)
        this.generationIV =
            convertPokemonIVGenerationModelToDB(pokemonSpriteGeneration.generationIV)
        this.generationV = convertPokemonVGenerationModelToDB(pokemonSpriteGeneration.generationV)
        this.generationVI =
            convertPokemonVIGenerationModelToDB(pokemonSpriteGeneration.generationVI)
        this.generationVII =
            convertPokemonVIIGenerationModelToDB(pokemonSpriteGeneration.generationVII)
        this.generationVIII =
            convertPokemonVIIIGenerationModelToDB(pokemonSpriteGeneration.generationVIII)

    }
}

fun convertPokemonIGenerationModelToDB(pokemonIGeneration: PokemonIGeneration?): PokemonIGenerationDB? {
    pokemonIGeneration?.let {
        return PokemonIGenerationDB().apply {
            this.redBlue = convertPokemonCommonSpriteModelToDB(pokemonIGeneration.redBlue)
            this.yellow = convertPokemonCommonSpriteModelToDB(pokemonIGeneration.yellow)
        }
    }
    return null
}

fun convertPokemonIIGenerationModelToDB(pokemonIIGeneration: PokemonIIGeneration?): PokemonIIGenerationDB? {
    pokemonIIGeneration?.let {
        return PokemonIIGenerationDB().apply {
            this.icons = pokemonIIGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.crystal = convertPokemonCommonSpriteModelToDB(pokemonIIGeneration.crystal)
            this.gold = convertPokemonCommonSpriteModelToDB(pokemonIIGeneration.gold)
            this.silver = convertPokemonCommonSpriteModelToDB(pokemonIIGeneration.silver)
        }
    }
    return null
}


fun convertPokemonIIIGenerationModelToDB(pokemonIIIGeneration: PokemonIIIGeneration?): PokemonIIIGenerationDB? {
    pokemonIIIGeneration?.let {
        return PokemonIIIGenerationDB().apply {
            this.icons = pokemonIIIGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.emerald = convertPokemonCommonSpriteModelToDB(pokemonIIIGeneration.emerald)
            this.rubySapphire =
                convertPokemonCommonSpriteModelToDB(pokemonIIIGeneration.rubySapphire)
            this.fireredLeafgreen =
                convertPokemonCommonSpriteModelToDB(pokemonIIIGeneration.fireredLeafgreen)
        }
    }
    return null
}

fun convertPokemonIVGenerationModelToDB(pokemonIVGeneration: PokemonIVGeneration?): PokemonIVGenerationDB? {
    pokemonIVGeneration?.let {
        return PokemonIVGenerationDB().apply {
            this.icons = pokemonIVGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.diamondPearl =
                convertPokemonCommonSpriteModelToDB(pokemonIVGeneration.diamondPearl)
            this.platinum = convertPokemonCommonSpriteModelToDB(pokemonIVGeneration.platinum)
            this.heartgoldSoulsilver =
                convertPokemonCommonSpriteModelToDB(pokemonIVGeneration.heartgoldSoulsilver)
        }
    }
    return null
}


fun convertPokemonVGenerationModelToDB(pokemonVGeneration: PokemonVGeneration?): PokemonVGenerationDB? {
    pokemonVGeneration?.let {
        return PokemonVGenerationDB().apply {
            this.icons = pokemonVGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.blackWhite = convertPokemonCommonSpriteModelToDB(pokemonVGeneration.blackWhite)
        }
    }
    return null
}

fun convertPokemonVIGenerationModelToDB(pokemonVIGeneration: PokemonVIGeneration?): PokemonVIGenerationDB? {
    pokemonVIGeneration?.let {
        return PokemonVIGenerationDB().apply {
            this.icons = pokemonVIGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.omegarubyAlphasapphire =
                convertPokemonCommonSpriteModelToDB(pokemonVIGeneration.omegarubyAlphasapphire)
            this.xy = convertPokemonCommonSpriteModelToDB(pokemonVIGeneration.xy)
        }
    }
    return null
}

fun convertPokemonVIIGenerationModelToDB(pokemonVIIGeneration: PokemonVIIGeneration?): PokemonVIIGenerationDB? {
    pokemonVIIGeneration?.let {
        return PokemonVIIGenerationDB().apply {
            this.icons = pokemonVIIGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
            this.ultraSunUltraMoon =
                convertPokemonCommonSpriteModelToDB(pokemonVIIGeneration.ultraSunUltraMoon)
        }
    }
    return null
}

fun convertPokemonVIIIGenerationModelToDB(pokemonVIIIGeneration: PokemonVIIIGeneration?): PokemonVIIIGenerationDB? {
    pokemonVIIIGeneration?.let {
        return PokemonVIIIGenerationDB().apply {
            this.icons = pokemonVIIIGeneration.icons?.let {
                convertPokemonCommonSpriteModelToDB(it)
            }
        }
    }
    return null
}
package com.pokemon.iquii.business.models.generation

import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIIGeneration : PokemonCommonGeneration() {

    val crystal: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val gold: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val silver: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
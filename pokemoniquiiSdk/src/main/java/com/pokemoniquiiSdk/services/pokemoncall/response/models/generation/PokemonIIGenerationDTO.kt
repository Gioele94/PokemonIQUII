package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIIGenerationDTO: PokemonCommonGenerationDTO() {

    val crystal: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val gold: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val silver: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
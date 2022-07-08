package com.pokemon.iquii.business.models.generation

import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonIVGeneration : PokemonCommonGeneration() {

    val platinum: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val diamondPearl: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()
    val heartgoldSoulsilver: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
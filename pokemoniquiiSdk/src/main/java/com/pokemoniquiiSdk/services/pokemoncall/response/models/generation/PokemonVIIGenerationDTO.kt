package com.pokemoniquiiSdk.services.pokemoncall.response.models.generation

import com.google.gson.annotations.SerializedName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonSpriteDTO

class PokemonVIIGenerationDTO: PokemonCommonGenerationDTO() {

    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: PokemonCommonSpriteDTO = PokemonCommonSpriteDTO()

}
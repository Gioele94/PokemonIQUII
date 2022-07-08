package com.pokemoniquiiSdk

import com.pokemoniquiiSdk.services.pokemoncall.PokemonCallService
import com.pokemoniquiiSdk.services.pokemoncall.PokemonCallServiceDAO


object PokemonIQUIIServiceFactory {

    val pokemonCallService: PokemonCallServiceDAO
        get() = PokemonCallService()

}

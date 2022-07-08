package com.pokemoniquiiSdk.services.pokemoncall

import com.pokemoniquiiSdk.PokemonIQUIIService
import com.pokemoniquiiSdk.common.intercept
import com.pokemoniquiiSdk.services.pokemoncall.endpoints.PokemonEndPoints
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseItem
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseList
import com.pokemoniquiiSdk.session.PokemonSessionManager
import io.reactivex.Single

class PokemonCallService : PokemonIQUIIService<PokemonEndPoints>(), PokemonCallServiceDAO {

    override fun getPokemons(limit: Int?, offset: Int?): Single<PokemonResponseList> {
        return PokemonSessionManager.executeRequest(
            getService(PokemonEndPoints::class.java).getPokemons(
                limit,
                offset,
            )
        ).intercept()
    }

    override fun getPokemonDetail(pokemonId: Int): Single<PokemonResponseItem> {
        return PokemonSessionManager.executeRequest(
            getService(PokemonEndPoints::class.java).getPokemonDetail(pokemonId)
        ).intercept()
    }

}
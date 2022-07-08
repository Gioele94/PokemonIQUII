package com.pokemoniquiiSdk.services.pokemoncall

import androidx.annotation.Nullable
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseItem
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseList
import io.reactivex.Single
import org.jetbrains.annotations.NotNull

interface PokemonCallServiceDAO {

    /**
     * GetPokemons
     * Get the collections of pokemons
     * @param limit return the number of elements
     * @param offset default 0, used with the limit parameter for return a list of collections.
     *               This param indicate the index start of list into the server.
     *               Example :
     *                  fisrt call -> get the first ten collections (index start is 0) --> limit = 10 & offset = 0
     *                  second call -> get the other ten collections (index start is 10) --> limit = 10 & offset = 10
     */
    fun getPokemons(
        @Nullable limit: Int?,
        @Nullable offset: Int?
    ): Single<PokemonResponseList>

    /**
     * GetPokemonDetail
     * Get the detail of pokemon by the id
     * @param pokemonId the id of pokemon that you want retrive the information
     */
    fun getPokemonDetail(@NotNull pokemonId: Int): Single<PokemonResponseItem>
}
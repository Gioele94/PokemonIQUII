package com.pokemoniquiiSdk.services.pokemoncall.endpoints

import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseItem
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonEndPoints {

    @GET("pokemon")
    fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Single<PokemonResponseList>

    @GET("pokemon/{pokemonId}")
    fun getPokemonDetail(@Path("pokemonId") pokemonId: Int): Single<PokemonResponseItem>

    /*
    @GET("pokemon/{pokemonAbilityId}")
    fun getPokemonAbilityDetail(@Path("pokemonAbilityId") idAbility: Int): Single<CollectionsResponse>

    @GET("pokemon/pokemon-species/{pokemonSpeciesId}")
    fun getPokemonSpeciesDetail(@Path("pokemonSpeciesId") idAbility: Int): Single<CollectionsResponse>

     */

}
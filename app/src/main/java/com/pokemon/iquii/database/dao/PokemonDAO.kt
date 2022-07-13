package com.pokemon.iquii.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pokemon.iquii.database.model.PokemonTableDB

@Dao
abstract class PokemonDAO : BaseDao<PokemonTableDB> {

    @Query("DELETE FROM pokemon")
    abstract fun deleteAllPokemons()

    @Query("SELECT * FROM pokemon")
    abstract fun getAllPokemons(): List<PokemonTableDB>?

    @Query("SELECT * FROM pokemon")
    abstract fun getAllPokemonsLiveData(): LiveData<List<PokemonTableDB>?>?

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    abstract fun getPokemon(pokemonId: Int): PokemonTableDB?

}
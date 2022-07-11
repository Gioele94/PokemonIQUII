package com.pokemon.iquii.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pokemon.iquii.database.model.PokemonDB

@Dao
abstract class PokemonDAO : BaseDao<PokemonDB>{

    @Query("DELETE FROM pokemon")
    abstract fun deleteAllPokemons()

    @Query("SELECT * FROM pokemon")
    abstract fun getAllPokemons(): List<PokemonDB>?

    @Query("SELECT * FROM pokemon")
    abstract fun getAllPokemonsLiveData(): LiveData<List<PokemonDB>?>?

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    abstract fun getPokemon(pokemonId : Int): PokemonDB?

}
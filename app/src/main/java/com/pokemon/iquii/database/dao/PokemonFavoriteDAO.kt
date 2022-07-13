package com.pokemon.iquii.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pokemon.iquii.database.model.PokemonTableFavoriteDB

@Dao
abstract class PokemonFavoriteDAO : BaseDao<PokemonTableFavoriteDB>{

    @Query("DELETE FROM pokemonFavorite")
    abstract fun deleteAllPokemons()

    @Query("SELECT * FROM pokemonFavorite")
    abstract fun getAllPokemons(): List<PokemonTableFavoriteDB>?

    @Query("SELECT * FROM pokemonFavorite")
    abstract fun getAllPokemonsLiveData(): LiveData<List<PokemonTableFavoriteDB>?>?

    @Query("SELECT * FROM pokemonFavorite WHERE id = :pokemonId")
    abstract fun getPokemon(pokemonId : Int): PokemonTableFavoriteDB?

}
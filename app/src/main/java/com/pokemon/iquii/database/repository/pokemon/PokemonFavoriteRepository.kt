package com.pokemon.iquii.database.repository.pokemon

import android.os.AsyncTask
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pokemon.iquii.database.dao.PokemonFavoriteDAO
import com.pokemon.iquii.database.database.DatabaseHelper
import com.pokemon.iquii.database.model.PokemonTableFavoriteDB

class PokemonFavoriteRepository {

    private val pokemonDAO: PokemonFavoriteDAO? = DatabaseHelper.INSTANCE?.getPokemonFavorite()

    val allPokemonsLiveDataVariable = getAllPokemonsLiveData()

    fun setObservable(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<PokemonTableFavoriteDB>?>
    ) {
        allPokemonsLiveDataVariable?.observe(lifecycleOwner, observer)
    }

    fun insert(pokemonDB: PokemonTableFavoriteDB) {
        pokemonDAO?.let { InsertPokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun insert(pokemonDBList: List<PokemonTableFavoriteDB>) {
        pokemonDAO?.let { InsertPokemonsAsyncTask(it).execute(pokemonDBList) }
    }

    fun update(pokemonDB: PokemonTableFavoriteDB) {
        pokemonDAO?.let { UpdatePokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun delete(pokemonDB: PokemonTableFavoriteDB) {
        pokemonDAO?.let { DeletePokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun deleteAllPokemons() {
        pokemonDAO?.let { DeleteAllPokemonsAsyncTask(it).execute() }
    }

    fun getAllPokemons(): List<PokemonTableFavoriteDB>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTask(it).execute().get() }
        return null
    }

    fun getAllPokemonsLiveData(): LiveData<List<PokemonTableFavoriteDB>?>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTaskLiveData(it).execute().get() }
        return null
    }

    fun getPokemon(pokemonID: Int): PokemonTableFavoriteDB? {
        pokemonDAO?.let { return GetPokemonAsyncTask(it, pokemonID).execute().get() }
        return null
    }

    private class InsertPokemonAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<PokemonTableFavoriteDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableFavoriteDB): Void? {
            pokemonDAO.insert(pokemonDB[0])
            return null
        }
    }

    private class InsertPokemonsAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<List<PokemonTableFavoriteDB>, Void, Void>() {

        override fun doInBackground(vararg pokemonListDB: List<PokemonTableFavoriteDB>): Void? {
            pokemonDAO.insert(pokemonListDB[0])
            return null
        }
    }

    private class UpdatePokemonAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<PokemonTableFavoriteDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableFavoriteDB): Void? {
            pokemonDAO.update(pokemonDB[0])
            return null
        }
    }

    private class DeletePokemonAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<PokemonTableFavoriteDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableFavoriteDB): Void? {
            pokemonDAO.delete(pokemonDB[0])
            return null
        }
    }

    private class DeleteAllPokemonsAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            pokemonDAO.deleteAllPokemons()
            return null
        }
    }

    private class GetAllPokemonsAsyncTask(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<Void, List<PokemonTableFavoriteDB>?, List<PokemonTableFavoriteDB>?>() {

        override fun doInBackground(vararg voids: Void): List<PokemonTableFavoriteDB>? {
            return pokemonDAO.getAllPokemons()
        }
    }

    private class GetAllPokemonsAsyncTaskLiveData(private val pokemonDAO: PokemonFavoriteDAO) :
        AsyncTask<Void, LiveData<List<PokemonTableFavoriteDB>?>?, LiveData<List<PokemonTableFavoriteDB>?>?>() {

        override fun doInBackground(vararg voids: Void): LiveData<List<PokemonTableFavoriteDB>?>? {
            return pokemonDAO.getAllPokemonsLiveData()
        }
    }

    private class GetPokemonAsyncTask(
        private val pokemonDAO: PokemonFavoriteDAO,
        private val pokemonId: Int
    ) : AsyncTask<Void, PokemonTableFavoriteDB?, PokemonTableFavoriteDB?>() {

        override fun doInBackground(vararg voids: Void): PokemonTableFavoriteDB? {
            return pokemonDAO.getPokemon(pokemonId)
        }
    }
}
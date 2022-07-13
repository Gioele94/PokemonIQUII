package com.pokemon.iquii.database.repository.pokemon

import android.os.AsyncTask
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pokemon.iquii.database.dao.PokemonDAO
import com.pokemon.iquii.database.database.DatabaseHelper
import com.pokemon.iquii.database.model.PokemonTableDB

class PokemonRepository {

    private val pokemonDAO: PokemonDAO? = DatabaseHelper.INSTANCE?.getPokemonDao()

    val allPokemonsLiveDataVariable = getAllPokemonsLiveData()

    fun setObservable(lifecycleOwner: LifecycleOwner, observer: Observer<List<PokemonTableDB>?>) {
        allPokemonsLiveDataVariable?.observe(lifecycleOwner, observer)
    }

    fun insert(pokemonDB: PokemonTableDB) {
        pokemonDAO?.let { InsertPokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun insert(pokemonDBList: List<PokemonTableDB>) {
        pokemonDAO?.let { InsertPokemonsAsyncTask(it).execute(pokemonDBList) }
    }

    fun update(pokemonDB: PokemonTableDB) {
        pokemonDAO?.let { UpdatePokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun delete(pokemonDB: PokemonTableDB) {
        pokemonDAO?.let { DeletePokemonAsyncTask(it).execute(pokemonDB) }
    }

    fun deleteAllPokemons() {
        pokemonDAO?.let { DeleteAllPokemonsAsyncTask(it).execute() }
    }

    fun getAllPokemons(): List<PokemonTableDB>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTask(it).execute().get() }
        return null
    }

    fun getAllPokemonsLiveData(): LiveData<List<PokemonTableDB>?>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTaskLiveData(it).execute().get() }
        return null
    }

    fun getPokemon(pokemonID: Int): PokemonTableDB? {
        pokemonDAO?.let { return GetPokemonAsyncTask(it, pokemonID).execute().get() }
        return null
    }

    private class InsertPokemonAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<PokemonTableDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableDB): Void? {
            pokemonDAO.insert(pokemonDB[0])
            return null
        }
    }

    private class InsertPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<List<PokemonTableDB>, Void, Void>() {

        override fun doInBackground(vararg pokemonListDB: List<PokemonTableDB>): Void? {
            pokemonDAO.insert(pokemonListDB[0])
            return null
        }
    }

    private class UpdatePokemonAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<PokemonTableDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableDB): Void? {
            pokemonDAO.update(pokemonDB[0])
            return null
        }
    }

    private class DeletePokemonAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<PokemonTableDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonTableDB): Void? {
            pokemonDAO.delete(pokemonDB[0])
            return null
        }
    }

    private class DeleteAllPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            pokemonDAO.deleteAllPokemons()
            return null
        }
    }

    private class GetAllPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) :
        AsyncTask<Void, List<PokemonTableDB>?, List<PokemonTableDB>?>() {

        override fun doInBackground(vararg voids: Void): List<PokemonTableDB>? {
            return pokemonDAO.getAllPokemons()
        }
    }

    private class GetAllPokemonsAsyncTaskLiveData(private val pokemonDAO: PokemonDAO) :
        AsyncTask<Void, LiveData<List<PokemonTableDB>?>?, LiveData<List<PokemonTableDB>?>?>() {

        override fun doInBackground(vararg voids: Void): LiveData<List<PokemonTableDB>?>? {
            return pokemonDAO.getAllPokemonsLiveData()
        }
    }

    private class GetPokemonAsyncTask(
        private val pokemonDAO: PokemonDAO,
        private val pokemonId: Int
    ) : AsyncTask<Void, PokemonTableDB?, PokemonTableDB?>() {

        override fun doInBackground(vararg voids: Void): PokemonTableDB? {
            return pokemonDAO.getPokemon(pokemonId)
        }
    }
}
package com.pokemon.iquii.database.repository.pokemon

import android.os.AsyncTask
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pokemon.iquii.database.dao.PokemonDAO
import com.pokemon.iquii.database.database.DatabaseHelper
import com.pokemon.iquii.database.model.PokemonDB

class PokemonRepository {

    private val pokemonDAO: PokemonDAO? = DatabaseHelper.INSTANCE?.getPokemonDao()

    val allPokemonsLiveDataVariable = getAllPokemonsLiveData()

    fun setObservable(lifecycleOwner: LifecycleOwner, observer: Observer<List<PokemonDB>?>){
        allPokemonsLiveDataVariable?.observe(lifecycleOwner, observer)
    }

    fun insert(pokemonDB: PokemonDB) { pokemonDAO?.let { InsertPokemonAsyncTask(it).execute(pokemonDB) }}

    fun insert(pokemonDBList: List<PokemonDB>) { pokemonDAO?.let { InsertPokemonsAsyncTask(it).execute(pokemonDBList) }}

    fun update(pokemonDB: PokemonDB) { pokemonDAO?.let { UpdatePokemonAsyncTask(it).execute(pokemonDB) }}

    fun delete(pokemonDB: PokemonDB) { pokemonDAO?.let { DeletePokemonAsyncTask(it).execute(pokemonDB) }}

    fun deleteAllPokemons() { pokemonDAO?.let { DeleteAllPokemonsAsyncTask(it).execute() }}

    fun getAllPokemons(): List<PokemonDB>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTask(it).execute().get()}
        return null
    }

    fun getAllPokemonsLiveData(): LiveData<List<PokemonDB>?>? {
        pokemonDAO?.let { return GetAllPokemonsAsyncTaskLiveData(it).execute().get()}
        return null
    }

    fun getPokemon(pokemonID: Int): PokemonDB? {
        pokemonDAO?.let { return GetPokemonAsyncTask(it, pokemonID).execute().get()}
        return null
    }

    private class InsertPokemonAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<PokemonDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonDB): Void? {
            pokemonDAO.insert(pokemonDB[0])
            return null
        }
    }

    private class InsertPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<List<PokemonDB>, Void, Void>() {

        override fun doInBackground(vararg pokemonListDB: List<PokemonDB>): Void? {
            pokemonDAO.insert(pokemonListDB[0])
            return null
        }
    }

    private class UpdatePokemonAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<PokemonDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonDB): Void? {
            pokemonDAO.update(pokemonDB[0])
            return null
        }
    }

    private class DeletePokemonAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<PokemonDB, Void, Void>() {

        override fun doInBackground(vararg pokemonDB: PokemonDB): Void? {
            pokemonDAO.delete(pokemonDB[0])
            return null
        }
    }

    private class DeleteAllPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            pokemonDAO.deleteAllPokemons()
            return null
        }
    }

    private class GetAllPokemonsAsyncTask(private val pokemonDAO: PokemonDAO) : AsyncTask<Void, List<PokemonDB>?, List<PokemonDB>?>() {

        override fun doInBackground(vararg voids: Void): List<PokemonDB>? {
            return pokemonDAO.getAllPokemons()
        }
    }

    private class GetAllPokemonsAsyncTaskLiveData(private val pokemonDAO: PokemonDAO) : AsyncTask<Void, LiveData<List<PokemonDB>?>?, LiveData<List<PokemonDB>?>?>() {

        override fun doInBackground(vararg voids: Void): LiveData<List<PokemonDB>?>? {
            return pokemonDAO.getAllPokemonsLiveData()
        }
    }

    private class GetPokemonAsyncTask(private val pokemonDAO: PokemonDAO, private val pokemonId : Int) : AsyncTask<Void, PokemonDB?, PokemonDB?>(){

        override fun doInBackground(vararg voids: Void): PokemonDB? {
            return pokemonDAO.getPokemon(pokemonId)
        }
    }
}
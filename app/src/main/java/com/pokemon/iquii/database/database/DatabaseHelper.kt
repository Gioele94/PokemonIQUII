package com.pokemon.iquii.database.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pokemon.iquii.database.dao.PokemonDAO
import com.pokemon.iquii.database.dao.PokemonFavoriteDAO

class DatabaseHelper constructor(context: Context) {

    var appDb: AppDataBase? = null
    val DB_NAME = "pokemon_db.db"

    init {
        appDb = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
        //addMigrations(MigrateDb(1, 2))
    }

    fun getPokemonDao(): PokemonDAO? {
        return appDb?.pokemonDao()
    }

    fun getPokemonFavorite(): PokemonFavoriteDAO? {
        return appDb?.pokemonFavoriteDao()
    }

    companion object {

        var INSTANCE: DatabaseHelper? = null
        fun init(context: Context): DatabaseHelper? {
            if (INSTANCE == null) {
                synchronized(DatabaseHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = DatabaseHelper(context)
                    }
                }
            }
            return INSTANCE
        }

    }

    fun onDestory() {
        if (appDb != null) {
            appDb?.close()
        }
    }

    class MigrateDb(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
        override fun migrate(database: SupportSQLiteDatabase) {
            Log.v("DbHelper", "migrate")
        }
    }

}
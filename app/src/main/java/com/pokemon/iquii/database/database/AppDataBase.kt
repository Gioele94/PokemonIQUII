package com.pokemon.iquii.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pokemon.iquii.database.converters.DataTypeConverter
import com.pokemon.iquii.database.dao.PokemonDAO
import com.pokemon.iquii.database.dao.PokemonFavoriteDAO
import com.pokemon.iquii.database.model.PokemonTableDB
import com.pokemon.iquii.database.model.PokemonTableFavoriteDB


@Database(entities = [PokemonTableDB::class, PokemonTableFavoriteDB::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    abstract fun pokemonFavoriteDao(): PokemonFavoriteDAO

}
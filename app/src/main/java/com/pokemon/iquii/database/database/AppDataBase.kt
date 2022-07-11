package com.pokemon.iquii.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pokemon.iquii.database.converters.DataTypeConverter
import com.pokemon.iquii.database.dao.PokemonDAO
import com.pokemon.iquii.database.model.PokemonDB


@Database(entities = [PokemonDB::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

}
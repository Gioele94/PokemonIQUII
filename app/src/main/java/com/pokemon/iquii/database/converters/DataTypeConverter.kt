package com.pokemon.iquii.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pokemon.iquii.database.model.*


class DataTypeConverter {

    @TypeConverter
    fun fromStringToAbilities(abilities: String): List<PokemonAbilityDB> {
        return Gson().fromJson(abilities, Array<PokemonAbilityDB>::class.java).toList()
    }

    @TypeConverter
    fun fromAbilitiesToString(abilities: List<PokemonAbilityDB>): String {
        return Gson().toJson(abilities);
    }

    @TypeConverter
    fun fromStringToPokemonCommonObjectListDB(value: String): List<PokemonCommonObjectDB> {
        return Gson().fromJson(value, Array<PokemonCommonObjectDB>::class.java).toList()
    }

    @TypeConverter
    fun fromPokemonCommonObjectListDBToString(value: List<PokemonCommonObjectDB>): String {
        return Gson().toJson(value);
    }

    @TypeConverter
    fun fromStringToPokemonMovesDB(value: String): List<PokemonMoveDB> {
        return Gson().fromJson(value, Array<PokemonMoveDB>::class.java).toList()
    }

    @TypeConverter
    fun fromPokemonMoveDBToString(value: List<PokemonMoveDB>): String {
        return Gson().toJson(value);
    }


    @TypeConverter
    fun fromStringToPokemonTypesDB(value: String): List<PokemonTypeImplDB> {
        return Gson().fromJson(value, Array<PokemonTypeImplDB>::class.java).toList()
    }

    @TypeConverter
    fun fromPokemonTypesDBToString(value: List<PokemonTypeImplDB>): String {
        return Gson().toJson(value);
    }

    @TypeConverter
    fun fromStringToPokemonStatDB(value: String): List<PokemonStatImplDB> {
        return Gson().fromJson(value, Array<PokemonStatImplDB>::class.java).toList()
    }

    @TypeConverter
    fun fromPokemonStatDBToString(value: List<PokemonStatImplDB>): String {
        return Gson().toJson(value);
    }

    @TypeConverter
    fun fromStringToPokemonCommonObjectDB(value: String): PokemonCommonObjectDB {
        return Gson().fromJson(value, PokemonCommonObjectDB::class.java)
    }

    @TypeConverter
    fun fromPokemonCommonObjectDBToString(value: PokemonCommonObjectDB): String {
        return Gson().toJson(value);
    }

    @TypeConverter
    fun fromStringToPokemonSpriteDB(value: String): PokemonSpriteDB {
        return Gson().fromJson(value, PokemonSpriteDB::class.java)
    }

    @TypeConverter
    fun fromPokemonSpriteDBToString(value: PokemonSpriteDB): String {
        return Gson().toJson(value);
    }


}
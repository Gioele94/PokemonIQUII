package com.pokemon.iquii.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pokemonFavorite", indices = [Index(value = ["id"], unique = true)])
open class PokemonTableFavoriteDB: PokemonDB() {

}
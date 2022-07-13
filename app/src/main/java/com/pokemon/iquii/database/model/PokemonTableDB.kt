package com.pokemon.iquii.database.model

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "pokemon", indices = [Index(value = ["id"], unique = true)])
open class PokemonTableDB: PokemonDB() {

}
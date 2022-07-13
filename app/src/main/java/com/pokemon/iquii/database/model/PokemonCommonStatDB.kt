package com.pokemon.iquii.database.model

import java.io.Serializable

open class PokemonCommonStatDB: Serializable {

    var name: StatNameDB = StatNameDB.HP
    var url: String = ""

}

enum class StatNameDB(val value: String){

    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed")

}
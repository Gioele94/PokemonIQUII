package com.pokemon.iquii.business.models

import java.io.Serializable

open class PokemonCommonStat: Serializable {

    var name: StatName = StatName.HP
    var url: String = ""

}

enum class StatName(val value: String){

    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed")

}
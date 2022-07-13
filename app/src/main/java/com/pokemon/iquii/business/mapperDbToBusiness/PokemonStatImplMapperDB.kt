package com.pokemon.iquii.business.mapperDbToBusiness

import com.pokemon.iquii.business.exception.InvalidEnumValue
import com.pokemon.iquii.business.models.PokemonCommonStat
import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemon.iquii.business.models.StatName
import com.pokemon.iquii.database.model.PokemonCommonStatDB
import com.pokemon.iquii.database.model.PokemonStatImplDB
import com.pokemon.iquii.database.model.StatNameDB

fun convertPokemonStatImplListDBToModel(list: List<PokemonStatImplDB>): List<PokemonStatImpl> {
    return list.map { convertPokemonStatImplDBToModel(it) }
}

fun convertPokemonStatImplDBToModel(pokemonStatImplDB: PokemonStatImplDB): PokemonStatImpl {
    return PokemonStatImpl().apply {
        this.baseStat = pokemonStatImplDB.baseStat
        this.effort = pokemonStatImplDB.effort
        this.stat = converPokemonCommonStatDBToModel(pokemonStatImplDB.stat)
    }
}

fun converPokemonCommonStatDBToModel(pokemonCommonStat: PokemonCommonStatDB): PokemonCommonStat {
    return PokemonCommonStat().apply {
        this.name = nameStatDBToNameStat(pokemonCommonStat.name)
        this.url = pokemonCommonStat.url
    }
}

@Throws(InvalidEnumValue::class)
fun nameStatDBToNameStat(statName: StatNameDB): StatName {
    return when (statName) {
        StatNameDB.HP -> StatName.HP
        StatNameDB.ATTACK -> StatName.ATTACK
        StatNameDB.DEFENSE -> StatName.DEFENSE
        StatNameDB.SPECIAL_ATTACK -> StatName.SPECIAL_ATTACK
        StatNameDB.SPECIAL_DEFENSE -> StatName.SPECIAL_DEFENSE
        StatNameDB.SPEED -> StatName.SPEED
        else -> throw InvalidEnumValue("Is impossible convert " + statName.name)
    }
}
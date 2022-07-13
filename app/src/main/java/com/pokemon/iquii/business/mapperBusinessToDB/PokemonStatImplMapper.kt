package com.pokemon.iquii.business.mapperBusinessToDB

import com.pokemon.iquii.business.exception.InvalidEnumValue
import com.pokemon.iquii.business.models.PokemonCommonStat
import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemon.iquii.business.models.StatName
import com.pokemon.iquii.database.model.PokemonCommonStatDB
import com.pokemon.iquii.database.model.PokemonStatImplDB
import com.pokemon.iquii.database.model.StatNameDB

fun convertPokemonStatImplListModelToDB(list: List<PokemonStatImpl>): List<PokemonStatImplDB> {
    return list.map { convertPokemonStatImplModelToDB(it) }
}

fun convertPokemonStatImplModelToDB(pokemonStatImpl: PokemonStatImpl): PokemonStatImplDB {
    return PokemonStatImplDB().apply {
        this.baseStat = pokemonStatImpl.baseStat
        this.effort = pokemonStatImpl.effort
        this.stat = converPokemonCommonStatModelToDB(pokemonStatImpl.stat)
    }
}


fun converPokemonCommonStatModelToDB(pokemonCommonStat: PokemonCommonStat): PokemonCommonStatDB {
    return PokemonCommonStatDB().apply {
        this.name = nameStatToNameStatDB(pokemonCommonStat.name)
        this.url = pokemonCommonStat.url
    }
}

@Throws(InvalidEnumValue::class)
fun nameStatToNameStatDB(statName: StatName): StatNameDB {
    return when (statName) {
        StatName.HP -> StatNameDB.HP
        StatName.ATTACK -> StatNameDB.ATTACK
        StatName.DEFENSE -> StatNameDB.DEFENSE
        StatName.SPECIAL_ATTACK -> StatNameDB.SPECIAL_ATTACK
        StatName.SPECIAL_DEFENSE -> StatNameDB.SPECIAL_DEFENSE
        StatName.SPEED -> StatNameDB.SPEED
        else -> throw InvalidEnumValue("Is impossible convert " + statName.name)
    }
}


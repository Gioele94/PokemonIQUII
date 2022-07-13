package com.pokemon.iquii.business.mapperDtoToBusiness

import com.pokemon.iquii.business.exception.InvalidEnumValue
import com.pokemon.iquii.business.models.PokemonCommonStat
import com.pokemon.iquii.business.models.PokemonStatImpl
import com.pokemon.iquii.business.models.StatName
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonCommonStatDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonStatImplDTO
import com.pokemoniquiiSdk.services.pokemoncall.response.models.StatNameDTO

fun convertPokemonStatImplListDtoToModel(list: List<PokemonStatImplDTO>): List<PokemonStatImpl> {
    return list.map { convertPokemonStatImplDtoToModel(it) }
}

fun convertPokemonStatImplDtoToModel(pokemonStatImplDTO: PokemonStatImplDTO): PokemonStatImpl {
    return PokemonStatImpl().apply {
        this.baseStat = pokemonStatImplDTO.baseStat
        this.effort = pokemonStatImplDTO.effort
        this.stat = converPokemonCommonStatDtoToModel(pokemonStatImplDTO.stat)
    }
}

fun converPokemonCommonStatDtoToModel(pokemonCommonStat: PokemonCommonStatDTO): PokemonCommonStat {
    return PokemonCommonStat().apply {
        this.name = nameStatDToToNameStat(pokemonCommonStat.name)
        this.url = pokemonCommonStat.url
    }
}

@Throws(InvalidEnumValue::class)
fun nameStatDToToNameStat(statName: StatNameDTO): StatName {
    return when (statName) {
        StatNameDTO.HP -> StatName.HP
        StatNameDTO.ATTACK -> StatName.ATTACK
        StatNameDTO.DEFENSE -> StatName.DEFENSE
        StatNameDTO.SPECIAL_ATTACK -> StatName.SPECIAL_ATTACK
        StatNameDTO.SPECIAL_DEFENSE -> StatName.SPECIAL_DEFENSE
        StatNameDTO.SPEED -> StatName.SPEED
        else -> throw InvalidEnumValue("Is impossible convert " + statName.name)
    }
}
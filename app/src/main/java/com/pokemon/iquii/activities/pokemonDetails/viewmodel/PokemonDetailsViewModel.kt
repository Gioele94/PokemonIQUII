package com.pokemon.iquii.activities.pokemonDetails.viewmodel

import android.content.Context
import androidx.databinding.Bindable
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.business.models.StatName

class PokemonDetailsViewModel(private val pokemon: Pokemon, private val context: Context) :
    PokemonCardViewModel(pokemon, context) {

    @Bindable
    fun getTypePokemon(): String {
        return pokemon.types.first().type.name
    }

    @Bindable
    fun getWeightPokemon(): String {
        return pokemon.weight.toString()
    }

    @Bindable
    fun getHeightPokemon(): String {
        return pokemon.height.toString()
    }

    @Bindable
    fun getHpValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.HP }.first().baseStat
    }

    @Bindable
    fun getAttackValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.ATTACK }.first().baseStat
    }

    @Bindable
    fun getDefenseValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.DEFENSE }.first().baseStat
    }

    @Bindable
    fun getSpecialAttackValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.SPECIAL_ATTACK }.first().baseStat
    }

    @Bindable
    fun getSpecialDefenseValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.SPECIAL_DEFENSE }.first().baseStat
    }

    @Bindable
    fun getSpeedValue(): Int{
        return pokemon.stats.filter { it.stat.name == StatName.SPEED }.first().baseStat
    }


}
package com.pokemon.iquii.business.models

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

open class Pokemon: Serializable {

    var baseExperience: Int = 0
    var isDefault = false
    var locationAreaEncounters: String = ""
    var abilities: List<PokemonAbility> = emptyList()
    var forms: List<PokemonCommonObject> = emptyList()
    var height: Int = 0
    var weight: Int = 0
    var id: Int = 0
    var name: String = ""
    var order: Int = 0
    var moves: List<PokemonMove> = emptyList()
    var species: PokemonCommonObject = PokemonCommonObject()
    var sprites: PokemonSprite = PokemonSprite()
    var stats: List<PokemonStatImpl> = emptyList()
    var types: List<PokemonTypeImpl> = emptyList()

    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<Any> = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(@NonNull oldItem: Any, @NonNull newItem: Any): Boolean {
                return (oldItem as Pokemon).id == (newItem as Pokemon).id
            }

            override fun areContentsTheSame(@NonNull oldItem: Any, @NonNull newItem: Any): Boolean {
                return oldItem as Pokemon == newItem as Pokemon
            }

            override fun getChangePayload(oldItem: Any, newItem: Any): Any? {
                oldItem as Pokemon
                newItem as Pokemon
                if (newItem == oldItem) {
                    return newItem
                }
                return oldItem
            }
        }
    }

}
package com.pokemon.iquii.activities.pokemons.viewmodel

import com.pokemon.iquii.activities.pokemons.actionlistener.PokemonsListActionListener
import com.pokemon.iquii.activities.pokemons.adapter.PokemonsListAdapter
import com.pokemon.iquii.business.mapperBusinessToDB.convertPokemonModelListToDB
import com.pokemon.iquii.business.mapperDtoToBusiness.convertPokemonDtoListToModel
import com.pokemon.iquii.business.mapperDtoToBusiness.convertPokemonDtoToModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapterWithDbOperation
import com.pokemon.iquii.components.viewmodel.CommonViewModelLoadItemsWithDB
import com.pokemon.iquii.database.repository.pokemon.PokemonRepository
import com.pokemoniquiiSdk.PokemonIQUIIServiceFactory
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonDTO
import io.reactivex.Single

class PokemonsListViewModel(
    actionListener: PokemonsListActionListener,
    listener: PokemonCardViewModel.Listener,
) : CommonViewModelLoadItemsWithDB<PokemonsListViewModel>(actionListener) {

    init {
        setLimit()
    }

    override val adapter: PokemonIquiiCommonAdapterWithDbOperation<*>? = PokemonsListAdapter().apply {
        this.listener = listener
        setHasStableIds(true)
    }

    override fun setLimit() {
        limit = 10
    }

    override val request: (() -> Single<*>?)? = {
        PokemonIQUIIServiceFactory.pokemonCallService.getPokemons(limit, offset)
    }

    override val request2: ((String) -> Single<*>?)? = {
        PokemonIQUIIServiceFactory.pokemonCallService.getPokemonDetail(it)
    }

    override fun convertListDtoToModel(items: List<*>): List<*> {
        return convertPokemonDtoListToModel(items.filterIsInstance<PokemonDTO>())
    }

    override fun convertObjectDtoToModel(item: Any): Any {
        return convertPokemonDtoToModel(item as PokemonDTO)
    }

    override fun deleteAllItemIntoDB() {
        PokemonRepository().deleteAllPokemons()
    }

    override fun insertAllItemIntoDB(items: List<*>) {
        PokemonRepository().insert(convertPokemonModelListToDB(items.filterIsInstance<Pokemon>()))
    }

}
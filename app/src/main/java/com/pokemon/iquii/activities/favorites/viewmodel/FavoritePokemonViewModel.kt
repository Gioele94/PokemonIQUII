package com.pokemon.iquii.activities.favorites.viewmodel

import com.pokemon.iquii.activities.favorites.adapter.PokemonsFavoriteListAdapter
import com.pokemon.iquii.activities.pokemons.actionlistener.PokemonsListActionListener
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonsListViewModel
import com.pokemon.iquii.business.mapperBusinessToDB.convertPokemonModelListToDB
import com.pokemon.iquii.business.mapperDtoToBusiness.convertPokemonDtoListToModel
import com.pokemon.iquii.business.mapperDtoToBusiness.convertPokemonDtoToModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapter
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapterWithDbOperation
import com.pokemon.iquii.components.viewmodel.CommonViewModelLoadItemsWithDB
import com.pokemon.iquii.database.model.PokemonDB
import com.pokemon.iquii.database.repository.pokemon.PokemonRepository
import com.pokemoniquiiSdk.services.pokemoncall.response.models.PokemonDTO
import io.reactivex.Single

class FavoritePokemonViewModel(
    actionListener: PokemonsListActionListener,
    listener: PokemonCardViewModel.Listener) : CommonViewModelLoadItemsWithDB<PokemonsListViewModel>(actionListener) {


    override val adapter: PokemonIquiiCommonAdapterWithDbOperation<*>? = PokemonsFavoriteListAdapter().apply {
        this.listener = listener
        setHasStableIds(true)
    }

    override val request: (() -> Single<*>?)? = null

    override val request2: ((String) -> Single<*>?)? = null

    override fun convertListDtoToModel(items: List<*>): List<*> {
        return convertPokemonDtoListToModel(items.filterIsInstance<PokemonDTO>())
    }

    override fun convertObjectDtoToModel(item: Any): Any {
        return convertPokemonDtoToModel(item as PokemonDTO)
    }

    override fun deleteAllItemIntoDB() {
    }

    override fun insertAllItemIntoDB(items: List<*>) {
    }

    override fun setLimit() {}

    override fun loadItem(
        clearDataSet: Boolean,
        getItemsFirstTime: Boolean,
        deleteItemIntoDB: Boolean
    ) {
        adapter?.setNetworkError(false)
        adapter?.setLoadingItemsFirstTime(false)
        val listDB = adapter?.getItemsFromDB() ?: emptyList<PokemonDB>()
        adapter?.addItemsFromDB(listDB, PokemonIquiiCommonAdapter.RemoveLoader.YES, clearDataSet)
        actionListener?.showSwipeRefreshLayout()
        actionListener?.onItemFetched()

    }

}
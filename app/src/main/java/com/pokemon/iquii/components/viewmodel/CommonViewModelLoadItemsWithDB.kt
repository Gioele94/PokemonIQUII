package com.pokemon.iquii.components.viewmodel

import android.annotation.SuppressLint
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapter
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapterWithDbOperation
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponse
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseItem
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CommonViewModelLoadItemsWithDB<T>(actionListener: PokemonIquiiListenerLoadItemCommon) :
    CommonViewModelWithDbOperation<PokemonIquiiListenerLoadItemCommon>(actionListener) {

    var limit = 10
    var offset = 0
    var canLoadMore = true
    var loading = false
    var endReached = false
    var firstReadDB = true

    abstract override val adapter: PokemonIquiiCommonAdapterWithDbOperation<*>?
    abstract val request: Function0<Single<*>?>?
    abstract val request2: Function1<String, Single<*>?>?
    abstract fun convertListDtoToModel(items: List<*>): List<*>
    abstract fun convertObjectDtoToModel(item: Any): Any

    abstract fun setLimit()

    override fun loadItem(
        clearDataSet: Boolean,
        getItemsFirstTime: Boolean,
        deleteItemIntoDB: Boolean
    ) {
        if (loading || !canLoadMore) return
        loading = true
        if (offset >= limit) {
            adapter?.addLoadingItem()
        }
        adapter?.setNetworkError(false)
        adapter?.setLoadingItemsFirstTime(getItemsFirstTime)

        val listDB = adapter?.getItemsFromDB()
        if (listDB != null && listDB != null && listDB.isNotEmpty() && firstReadDB) {
            firstReadDB = false
            adapter?.addItemsFromDB(listDB, PokemonIquiiCommonAdapter.RemoveLoader.YES, clearDataSet)
            actionListener?.showSwipeRefreshLayout()
        }

        request?.invoke()?.let {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ itemsResponse ->
                    val items = {
                        when (itemsResponse) {
                            is PokemonResponseList -> itemsResponse.results
                            else -> null
                        }
                    }.run { invoke() }
                    if (request2 != null && items != null) {
                        startSecondCall(items, clearDataSet, getItemsFirstTime, deleteItemIntoDB, 0, mutableListOf())
                    } else {
                        actionListener?.onItemFetched()
                        adapter?.setLoadingItemsFirstTime(false)
                        loading = false
                        if (items != null) {
                            val itemList = convertListDtoToModel(listOf(*items as Array<*>))
                            if (deleteItemIntoDB) {
                                deleteAllItemIntoDB()
                                adapter?.clear()
                            }
                            adapter?.addItems(
                                itemList,
                                PokemonIquiiCommonAdapter.RemoveLoader.YES,
                                clearDataSet,
                                false,
                                null
                            )
                            insertAllItemIntoDB(itemList)
                            canLoadMore = itemList.size == limit
                            endReached = itemList.isEmpty()
                        } else {
                            if (offset == 0) {
                                /** For notify that is empty list **/
                                adapter?.clear()
                            }
                            adapter?.removeLoadingItem()
                            canLoadMore = false
                            endReached = true
                            adapter?.removeLoadingItem()
                        }

                        offset += limit
                    }

                }, { throwable ->
                    if (adapter?.getDataSetSize() == 0) {
                        adapter?.setLoadingItemsFirstTime(true)
                        adapter?.setNetworkError(true)
                    } else {
                        actionListener?.onInternetNetworkError()
                    }
                    loading = false
                    actionListener?.onItemFetched()
                    adapter?.removeLoadingItem()
                })
        }
    }

    @SuppressLint("CheckResult")
    private fun startSecondCall(
        pokemonsList: Array<PokemonResponse>,
        clearDataSet: Boolean,
        getItemsFirstTime: Boolean,
        deleteItemIntoDB: Boolean,
        currentIndex: Int = 0,
        pokemonDetailList: MutableList<Pokemon>
    ) {
        request2?.invoke(pokemonsList[currentIndex].url.split("/").filter { it.isNotEmpty() }.last())?.let {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ itemsResponse ->
                    val item = {
                        when (itemsResponse) {
                            is PokemonResponseItem -> itemsResponse
                            else -> null
                        }
                    }.run { invoke() }

                    if (item != null) {

                        pokemonDetailList.add(convertObjectDtoToModel(item) as Pokemon)
                        if (currentIndex < pokemonsList.size - 1) {
                            startSecondCall(
                                pokemonsList,
                                clearDataSet,
                                getItemsFirstTime,
                                deleteItemIntoDB,
                                currentIndex + 1,
                                pokemonDetailList
                            )
                        } else {
                            actionListener?.onItemFetched()
                            adapter?.setLoadingItemsFirstTime(false)
                            loading = false
                            if (deleteItemIntoDB) {
                                deleteAllItemIntoDB()
                                adapter?.clear()
                            }
                            adapter?.addItems(
                                pokemonDetailList,
                                PokemonIquiiCommonAdapter.RemoveLoader.YES,
                                clearDataSet,
                                false,
                                null
                            )
                            insertAllItemIntoDB(pokemonDetailList)
                            canLoadMore = pokemonDetailList.size == limit
                            endReached = pokemonDetailList.isEmpty()
                        }
                        offset += limit
                    }
                }, { throwable ->
                    if (adapter?.getDataSetSize() == 0) {
                        adapter?.setLoadingItemsFirstTime(true)
                        adapter?.setNetworkError(true)
                    } else {
                        actionListener?.onInternetNetworkError()
                    }
                    loading = false
                    actionListener?.onItemFetched()
                    adapter?.removeLoadingItem()
                })
        }
    }

    fun isLoading() = loading

    override fun isEndReached() = endReached

    override fun stopLoading() {
        loading = false
        adapter?.removeLoadingItem()
    }

    override fun reset(getItemsFirstTime: Boolean) {
        offset = 0
        canLoadMore = true
        loading = false
        endReached = false
        loadItem(true, getItemsFirstTime, true)
    }
}
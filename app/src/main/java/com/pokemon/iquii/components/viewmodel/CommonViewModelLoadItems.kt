package com.pokemon.iquii.components.viewmodel

import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapter
import com.pokemoniquiiSdk.services.pokemoncall.response.PokemonResponseList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CommonViewModelLoadItems<T>(actionListener: PokemonIquiiListenerLoadItemCommon) : CommonListViewModel<PokemonIquiiListenerLoadItemCommon>(actionListener) {

    var limit = 10
    var offset = 0
    var canLoadMore = true
    var loading = false
    var endReached = false

    abstract override val adapter: PokemonIquiiCommonAdapter<*>?
    abstract val request: Function0<Single<*>?>?
    abstract fun convertListDtoToModel(items: List<*>): List<*>

    abstract fun setLimit()

    override fun loadItem(clearDataSet: Boolean, getItemsFirstTime: Boolean, deleteItemIntoDB: Boolean) {
        if (loading || !canLoadMore) return
        loading = true
        if (offset >= limit) {
            adapter?.addLoadingItem()
        }
        adapter?.setNetworkError(false)
        adapter?.setLoadingItemsFirstTime(getItemsFirstTime)
        request?.invoke()?.let {
                it.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io()).subscribe({ itemsResponse ->
                            val items = {
                                when (itemsResponse) {
                                    is PokemonResponseList -> itemsResponse.results
                                    else -> null
                                }
                            }.run { invoke() }
                            actionListener?.onItemFetched()
                            adapter?.setLoadingItemsFirstTime(false)
                            loading = false
                            if (items != null) {
                                val itemList = convertListDtoToModel(listOf(*items as Array<*>))
                                adapter?.addItems(itemList, PokemonIquiiCommonAdapter.RemoveLoader.YES, clearDataSet, false, null)
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
            } ?: run{
            adapter?.clear()
            stopLoading()
            actionListener?.onItemFetched()
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
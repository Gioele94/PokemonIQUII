package com.pokemon.iquii.activities.pokemons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.pokemon.iquii.BR
import com.pokemon.iquii.activities.pokemons.viewmodel.PokemonCardViewModel
import com.pokemon.iquii.business.mapperBusinessToDB.convertPokemonModelToDB
import com.pokemon.iquii.business.mapperDbToBusiness.convertPokemonDBListToModel
import com.pokemon.iquii.business.models.Pokemon
import com.pokemon.iquii.components.actionlistener.ReloadListener
import com.pokemon.iquii.components.adapter.PokemonIquiiCommonAdapterWithDbOperation
import com.pokemon.iquii.components.emptylist.viewmodel.EmptyListViewModel
import com.pokemon.iquii.components.loadingdatafirsttime.viewmodel.LoadDataFirstTime
import com.pokemon.iquii.database.model.PokemonDB
import com.pokemon.iquii.database.repository.pokemon.PokemonRepository
import com.pokemon.iquii.databinding.PokemonCardBinding


class PokemonsListAdapter :
    PokemonIquiiCommonAdapterWithDbOperation<PokemonsListAdapter>(Pokemon.DIFF_CALLBACK) {

    var listener: PokemonCardViewModel.Listener? = null
    override var reloadListener: ReloadListener? = null

    override fun getItemByIdItem(itemId: Int): Pokemon? {
        return dataSet.value?.find { (it as? Pokemon)?.id == itemId } as? Pokemon
    }

    override fun removeItemByItem(item: Any) {
        synchronized(dataSet) {
            dataSet.value?.remove(item as Pokemon)
            PokemonRepository().delete(convertPokemonModelToDB(item as Pokemon))
            notifyItemRangeChanged(0, itemCount)
        }
    }

    override fun addItemsFromDB(items: List<*>, removeLoader: RemoveLoader, clearDataSet: Boolean) {
        addItems(
            convertPokemonDBListToModel(items.filterIsInstance<PokemonDB>()),
            removeLoader,
            clearDataSet,
            false,
            null
        )
    }

    override fun replaceDataSetWithItemsDB() {
        dataSet.value?.clear()
        getItemsFromDB()?.let { dataSet.value?.addAll(it) }
        notifyItemRangeChanged(0, itemCount)
    }

    override fun getItemsFromDB(): List<PokemonDB>? {
        return PokemonRepository().getAllPokemons()
    }

    override fun getItemsFromDBLiveData(): LiveData<out List<*>?>? {
        return PokemonRepository().getAllPokemonsLiveData()
    }

    override fun initItemCardViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = PokemonCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonCardViewHolder(binding)
    }

    override fun updateItemIntoDBByItem(item: Any) {
        PokemonRepository().update(convertPokemonModelToDB(item as Pokemon))
    }

    override fun updateItemByItem(item: Any?, updateItemIntoDB: Boolean) {
        val filterItem = item as? Pokemon
        synchronized(dataSet) {
            val index = dataSet.value?.indexOf(filterItem) ?: -1
            if (index != -1) {
                dataSet.value!![index] = filterItem
            }
        }
        filterItem?.let { updateItemIntoDBByItem(it) }
    }

    override fun onBindViewHolderCustom(holder: RecyclerView.ViewHolder, position: Int) {
        synchronized(dataSet) {
            if (holder is PokemonCardViewHolder) {
                bindPokemonCard(holder, position)
            }
        }
    }

    override fun setEmptyListVIewModelNoItems(): EmptyListViewModel? {
        return EmptyListViewModel()
    }

    private fun bindPokemonCard(holder: PokemonCardViewHolder, position: Int) {
        val currentNotification = dataSet.value?.let { it[position] } as? Pokemon
        val viewModel =
            currentNotification?.let { PokemonCardViewModel(it, holder.itemView.context) }
        viewModel?.listener = listener
        viewModel?.let { holder.setViewModel(it) }
        setupAnimation(position, holder)
    }

    override fun loadingDataFirstTimeViewModel(): LoadDataFirstTime? {
        return LoadDataFirstTime()
    }

    override fun getItemViewTypeCustom(position: Int): Int {
        return try {
            if (dataSet.value?.let { it[position] } != null) {
                TYPE_ITEM
            } else {
                TYPE_LOADING_ITEM
            }
        } catch (e: IndexOutOfBoundsException) {
            TYPE_LOADING_ITEM
        }
    }

    override fun getItemCountCustom(): Int {
        synchronized(dataSet) {
            return if (dataSet.value?.isEmpty() == true) 1 else dataSet.value?.size ?: 1
        }
    }

    override fun addItems(
        items: List<*>,
        removeLoader: RemoveLoader,
        clearDataSet: Boolean,
        addNewItem: Boolean,
        callBack: (() -> Unit)?
    ) {
        val itemFilter = items.filterIsInstance<Pokemon>()
        removeLoadingItem()
        synchronized(dataSet) {
            if (clearDataSet) {
                clear()
            }
            for (item in itemFilter) {
                if (dataSet.value?.contains(item) == false) {
                    dataSet.value?.add(item)
                }
            }
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class PokemonCardViewHolder(internal val binding: PokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @Synchronized
        fun setViewModel(viewModel: PokemonCardViewModel) {
            this.binding.setVariable(BR.viewModel, viewModel)
            this.binding.executePendingBindings()
        }
    }
}

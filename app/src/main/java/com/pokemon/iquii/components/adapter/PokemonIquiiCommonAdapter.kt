package com.pokemon.iquii.components.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pokemon.iquii.R
import com.pokemon.iquii.components.actionlistener.ReloadListener
import com.pokemon.iquii.components.emptylist.viewmodel.EmptyListViewModel
import com.pokemon.iquii.components.loadingdatafirsttime.viewmodel.LoadDataFirstTime
import com.pokemon.iquii.components.networkerror.viewmodel.NetworkErrorViewModel
import com.pokemon.iquii.database.model.PokemonDB
import com.pokemon.iquii.databinding.EmptyListBinding
import com.pokemon.iquii.databinding.LoadingItemsFirstTimeBinding
import com.pokemon.iquii.databinding.NetworkErrorBinding

abstract class PokemonIquiiCommonAdapter<T>(diffUtilCallback : DiffUtil.ItemCallback<Any>) : ListAdapter<Any, RecyclerView.ViewHolder>(diffUtilCallback){

    /** view type variable for recycle view **/
    val TYPE_NO_ITEMS_AVAILABLE = 0
    val TYPE_HEADER = 1
    val TYPE_ITEM = 2
    val TYPE_LOADING_ITEM = 3
    val TYPE_GET_ITEMS_FIRST_TIME = 4
    val TYPE_NETWORK_ERROR = 5

    private var getItemsFirstTime: Boolean = false
    private var networkError: Boolean = false

    abstract var reloadListener: ReloadListener?
    var dataSet: MutableLiveData<MutableList<Any?>> = MutableLiveData()
    var dataSetFromDB: MutableLiveData<MutableList<PokemonDB>?> = MutableLiveData()
    private var lastAnimatedPosition: Int = -1

    init {
        dataSet.value = emptyList<Any?>().toMutableList()
        dataSetFromDB.value = emptyList<PokemonDB>().toMutableList()
    }

    /** abstract method and variable **/
    abstract fun getItemByIdItem(itemId: Int): Any?
    abstract fun updateItemByItem(item: Any?, updateItemIntoDB: Boolean)
    abstract fun removeItemByItem(item: Any)
    abstract fun addItems(items: List<*>, removeLoader: RemoveLoader, clearDataSet: Boolean, addNewItem: Boolean = false, callBack: (() -> Unit)? = null)
    abstract fun getItemCountCustom() : Int
    abstract fun getItemViewTypeCustom(position: Int): Int
    abstract fun initItemCardViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun onBindViewHolderCustom(holder: RecyclerView.ViewHolder, position: Int)
    abstract fun setEmptyListVIewModelNoItems() : EmptyListViewModel?
    abstract fun loadingDataFirstTimeViewModel() : LoadDataFirstTime?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOADING_ITEM -> initLoadingItemsViewHolder(parent)
            TYPE_ITEM -> initItemCardViewHolder(parent)
            TYPE_NO_ITEMS_AVAILABLE -> initNoItemViewHolder(parent)
            TYPE_GET_ITEMS_FIRST_TIME -> initGetItemsFirstTime(parent)
            TYPE_NETWORK_ERROR -> initNetworkError(parent)
            else -> throw UnsupportedOperationException("view type not recognized")
        }
    }

    private fun initNoItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = EmptyListBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return NoItemsViewHolder<T>(binding)
    }

    private fun initGetItemsFirstTime(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = LoadingItemsFirstTimeBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return LoadingItemsFirstTimeViewHolder<T>(binding)

    }

    fun getDataSetSize(): Int { return dataSet.value?.size ?: 0 }

    fun addLoadingItem() {
        synchronized(dataSet) {
            if (dataSet.value?.contains(null) == false) {
                dataSet.value?.add(null)
                notifyItemRangeChanged(0, itemCount)
            }
        }
    }

    fun setLoadingItemsFirstTime(getItemsFirstTime: Boolean) { this.getItemsFirstTime = getItemsFirstTime }

    fun setNetworkError(networkError: Boolean) { this.networkError = networkError }

    fun clear() {
        val size = getItemCountCustom()
        dataSet.value = null
        dataSet.value = mutableListOf()
        lastAnimatedPosition = -1
        notifyItemRangeRemoved(0, size)
    }

    enum class RemoveLoader {
        YES,
        NO
    }

    fun removeLoadingItem() {
        synchronized(dataSet) {
            val indexOfLoadingItem = dataSet.value?.indexOf(null) ?: -1
            if (indexOfLoadingItem != -1) {
                dataSet.value?.removeAt(indexOfLoadingItem)
                notifyItemRangeChanged(0, itemCount)
            }
        }
    }

    override fun getItemId(position: Int): Long { return position.toLong() }

    private fun initNetworkError(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = NetworkErrorBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return NetworkErrorViewHolder<T>(binding)
    }

    private fun initLoadingItemsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.circular_progress_bar, parent, false)
        return LoadingItemsViewHolder<T>(view)
    }

    override fun getItemCount(): Int { synchronized(dataSet) { return getItemCountCustom() } }

    override fun getItemViewType(position: Int): Int {
        synchronized(dataSet) {
            return if (dataSet.value?.isEmpty() == true) {
                if (getItemsFirstTime && networkError) {
                    TYPE_NETWORK_ERROR
                } else if (getItemsFirstTime && !networkError) {
                    TYPE_GET_ITEMS_FIRST_TIME
                } else {
                    TYPE_NO_ITEMS_AVAILABLE
                }
            } else {
                getItemViewTypeCustom(position)
            }
        }
    }

    fun setupAnimation(position: Int, holder : RecyclerView.ViewHolder){
        if (position > lastAnimatedPosition) {
            val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation_slide_from_bottom)
            holder.itemView.startAnimation(animation)
            lastAnimatedPosition = position
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(arg0: Animation) {}
                override fun onAnimationRepeat(arg0: Animation) {}
                override fun onAnimationEnd(arg0: Animation) {}
            })
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        synchronized(dataSet) {
            when (holder) {
                is PokemonIquiiCommonAdapter<*>.NoItemsViewHolder<*> -> bindNoItem(holder as NoItemsViewHolder<T>)
                is PokemonIquiiCommonAdapter<*>.LoadingItemsFirstTimeViewHolder<*> -> bindLoadingItemsFirstTime(holder as LoadingItemsFirstTimeViewHolder<T>)
                is PokemonIquiiCommonAdapter<*>.LoadingItemsViewHolder<*> -> bindLoadingItemView(holder as LoadingItemsViewHolder<*>)
                is PokemonIquiiCommonAdapter<*>.NetworkErrorViewHolder<*> -> bindNetworkError(holder as NetworkErrorViewHolder<T>)
                else -> onBindViewHolderCustom(holder, position)
            }
        }
    }

    private fun bindNetworkError(holder: NetworkErrorViewHolder<T>) {
        val viewModel = NetworkErrorViewModel()
        viewModel.listener = reloadListener
        holder.setViewModel(viewModel)
    }

    private fun bindNoItem(holder: NoItemsViewHolder<T>) {
        setEmptyListVIewModelNoItems()?.let { holder.setEmptyListViewModel(it).apply { it.listener  = reloadListener }}
    }

    private fun bindLoadingItemView(holder: LoadingItemsViewHolder<*>) {}

    private fun bindLoadingItemsFirstTime(holder: LoadingItemsFirstTimeViewHolder<T>) {
        loadingDataFirstTimeViewModel()?.let { holder.setLoadingItemsFirstTimeViewModel(it) }
    }

    private inner class NetworkErrorViewHolder<T>(val binding: NetworkErrorBinding) : RecyclerView.ViewHolder(binding.root) {

        @Synchronized
        fun setViewModel(viewModel: NetworkErrorViewModel) {
            this.binding.setVariable(BR.viewModel, viewModel)
            this.binding.executePendingBindings()
        }
    }

    private inner class LoadingItemsFirstTimeViewHolder<T>(internal val binding: LoadingItemsFirstTimeBinding) : RecyclerView.ViewHolder(binding.root) {

        @Synchronized
        fun setLoadingItemsFirstTimeViewModel(loadDataFirstTime: LoadDataFirstTime) {
            this.binding.setVariable(BR.loadingLoadDataFirstTime, loadDataFirstTime)
            this.binding.executePendingBindings()
        }
    }

    private inner class LoadingItemsViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView)

    private inner class NoItemsViewHolder<T>(val binding: EmptyListBinding) : RecyclerView.ViewHolder(binding.root) {

        @Synchronized
        fun setEmptyListViewModel(emptyListViewModel: EmptyListViewModel) {
            this.binding.setVariable(BR.emptyListViewModel, emptyListViewModel)
            this.binding.executePendingBindings()
        }
    }
}
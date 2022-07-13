package com.pokemon.iquii.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.pokemon.iquii.R
import com.pokemon.iquii.components.actionlistener.PokemonIquiiListenerLoadItemCommon
import com.pokemon.iquii.components.activity.PokemonIquiiActivity
import com.pokemon.iquii.components.layoutmanager.SpeedyLinearLayoutManager
import com.pokemon.iquii.components.viewmodel.CommonListViewModel
import com.pokemon.iquii.databinding.CommonListFragmentBinding

abstract class PokemonIquiiListFragment<T> : PokemonIquiiGenericFragment(), PokemonIquiiListenerLoadItemCommon {

    var binding: CommonListFragmentBinding? = null
    abstract val viewModel: CommonListViewModel<*>?
    abstract val showLineDivide: Boolean
    abstract val removeItemDecoration: Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = CommonListFragmentBinding.inflate(inflater, container, false)
            setupRecycle()
        }
        return binding?.root
    }

    fun setupRecycle(binding: CommonListFragmentBinding) {
        this.binding = binding
        setupRecycle()
    }

    private fun setupRecycle() {
        binding?.viewModel = viewModel
        binding?.recyclerView?.adapter = viewModel?.adapter
        binding?.recyclerView?.setHasFixedSize(false)
        val animator: ItemAnimator? = binding?.recyclerView?.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }

        val layoutManager =
            activity?.let { SpeedyLinearLayoutManager(it, LinearLayoutManager.VERTICAL, false) }
        binding?.recyclerView?.layoutManager = layoutManager
        if (showLineDivide) {
            activity?.let {

                binding?.recyclerView?.addItemDecoration(
                    DividerItemDecoration(
                        it,
                        if(removeItemDecoration) 0 else LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
        binding?.recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager?.itemCount
                val lastVisibleItem = layoutManager?.findLastVisibleItemPosition()

                if (!viewModel?.isEndReached()!! && totalItemCount!! <= lastVisibleItem!! + 2) {
                    if (!viewModel?.isEndReached()!!) {
                        viewModel?.loadItem(
                            clearDataSet = false,
                            getItemsFirstTime = false,
                            deleteItemIntoDB = false
                        )
                    } else {
                        viewModel?.stopLoading()
                    }
                }
            }
        })

        binding?.viewModel?.adapter?.dataSet?.observe(
            (activity as PokemonIquiiActivity),
            Observer<MutableList<Any?>> { list ->
                binding?.viewModel?.adapter?.submitList(list?.toList())
            })

        binding?.swipeContainer?.isEnabled = true
        binding?.swipeContainer?.setColorSchemeColors(
            ResourcesCompat.getColor(
                resources,
                R.color.error,
                null
            )
        )
        binding?.swipeContainer?.isRefreshing = false
        binding?.swipeContainer?.setOnRefreshListener {
            if (viewModel?.adapter?.getDataSetSize() != 0) {
                viewModel?.reset(false)
            } else {
                viewModel?.reset(true)
            }
        }

        viewModel?.loadItem(clearDataSet = true, getItemsFirstTime = true, deleteItemIntoDB = true)
    }

    override fun onItemFetched() {
        binding?.swipeContainer?.isRefreshing = false
    }

    override fun showSwipeRefreshLayout() {
        binding?.swipeContainer?.isRefreshing = true
    }

}

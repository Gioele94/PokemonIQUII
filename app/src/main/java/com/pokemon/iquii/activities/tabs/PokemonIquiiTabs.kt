package com.pokemon.iquii.activities.tabs

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pokemon.iquii.R
import com.pokemon.iquii.activities.favorites.view.PokemonsFavoriteGallery
import com.pokemon.iquii.activities.pokemons.view.PokemonsGallery
import com.pokemon.iquii.activities.tabs.adapter.PokemonIquiiTabsAdapter
import com.pokemon.iquii.components.activity.PokemonIquiiActivity
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer

class PokemonIquiiTabs : PokemonIquiiActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager
    lateinit var labelToolbar: TextView

    override val listenerSearch: ListenerSearch?
        get() = object : ListenerSearch {

            override fun onSearchBar(find: String?) {
                when (viewPager.currentItem) {
                    0 -> {
                        ((viewPager.adapter as PokemonIquiiTabsAdapter).getItem(0) as PokemonsGallery).viewModel.adapter?.filteredDataSetByQuery(
                            find
                        )
                    }
                    1 -> {
                        ((viewPager.adapter as PokemonIquiiTabsAdapter).getItem(1) as PokemonsFavoriteGallery).viewModel.adapter?.filteredDataSetByQuery(
                            find
                        )
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.navigation_drawer)

        bottomNavigationView = findViewById(R.id.main_nav)
        labelToolbar = findViewById(R.id.PokemonTabsToolbarText)
        viewPager = findViewById(R.id.viewpager)

        viewPager.addOnPageChangeListener(this)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        setupViewPager(viewPager)

        if (savedInstanceState == null) {
            onNavigationItemSelected(bottomNavigationView.menu.findItem(R.id.nav_pokemons))
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pokemons -> viewPager.currentItem = item.order
            R.id.nav_favorites -> viewPager.currentItem = item.order
        }
        return true
    }


    override fun onPageScrollStateChanged(state: Int) { /* Nothing to do */
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) { /* Nothing to do */
    }

    override fun onPageSelected(position: Int) {
        val prev = bottomNavigationView.selectedItemId
        val item = bottomNavigationView.menu.getItem(position)

        when (prev) {
            R.id.nav_pokemons -> bottomNavigationView.menu.getItem(0)
                .setIcon(R.drawable.ic_list_bullet)
            R.id.nav_favorites -> bottomNavigationView.menu.getItem(1)
                .setIcon(R.drawable.ic_bookmarks)
        }

        item.isChecked = true
        when (item.itemId) {
            R.id.nav_pokemons -> item.setIcon(R.drawable.ic_list_bullet_active)
            R.id.nav_favorites -> item.setIcon(R.drawable.ic_bookmarks_active)
        }

        when (position) {
            0 -> labelToolbar.text = PokemonIQUIILocalizer.get(R.string.pokemonTab_pokemons, this)
            1 -> labelToolbar.text = PokemonIQUIILocalizer.get(R.string.pokemonTab_favourites, this)
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = PokemonIquiiTabsAdapter(supportFragmentManager)
        adapter.addFragment(PokemonsGallery.newInstance())
        adapter.addFragment(PokemonsFavoriteGallery.newInstance())
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        viewPager.post { onPageSelected(viewPager.currentItem) }

    }

    fun getAdapter(): PagerAdapter? {
        return viewPager.adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
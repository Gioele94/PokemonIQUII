package com.pokemon.iquii.components.tab

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout


class PokemonIquiiTabLayout : TabLayout {

    constructor(context: Context) : super(context) {
        this.setup(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.setup(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setup(attrs, defStyleAttr)
    }

    private fun setup(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) {
            return
        }

    }

    override fun addTab(tab: Tab) {
        super.addTab(tab)
        val mainView = getChildAt(0) as ViewGroup
        val tabView = mainView.getChildAt(tab.position) as ViewGroup
        val tabChildCount = tabView.childCount
    }

}
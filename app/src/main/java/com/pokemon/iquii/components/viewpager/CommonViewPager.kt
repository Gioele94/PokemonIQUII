package com.pokemon.iquii.components.viewpager

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class CommonViewPager : ViewPager {

    private var swipeEnabled = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (swipeEnabled) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (swipeEnabled) {
            super.onTouchEvent(event)
        } else false
    }

    fun setSwipeEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }
}

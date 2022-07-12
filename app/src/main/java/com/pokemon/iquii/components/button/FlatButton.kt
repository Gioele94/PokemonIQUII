package com.pokemon.iquii.components.button

import android.content.Context
import android.util.AttributeSet
import com.pokemon.iquii.R

class FlatButton : androidx.appcompat.widget.AppCompatButton {

    constructor(context: Context) : super(context, null, 0) {
        this.setup(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        this.setup(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setup(attrs, defStyleAttr)
    }

    private fun setup(attrs: AttributeSet?, defStyleAttr: Int) {
        this.isAllCaps = false
        this.setTextColor(resources.getColorStateList(R.color.error))
        background = null
        val p = resources.getDimensionPixelOffset(R.dimen.margin_normal)
        setPadding(p, p, p, p)
    }

}

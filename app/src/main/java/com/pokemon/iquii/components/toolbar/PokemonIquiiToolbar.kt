package com.pokemon.iquii.components.toolbar

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.pokemon.iquii.R

class PokemonIquiiToolbar : Toolbar {

    private var titleTextView: TextView? = null
    private var screenWidth: Int = 0

    private val screenSize: Point
        get() {
            val wm = context.getSystemService(WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val screenSize = Point()
            display.getSize(screenSize)
            return screenSize
        }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        screenWidth = screenSize.x
        titleTextView = TextView(context)
        titleTextView?.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.text_size_medium)
        )
        titleTextView?.isSingleLine = true
        titleTextView?.maxLines = 1
        titleTextView?.ellipsize = TextUtils.TruncateAt.END
        addView(titleTextView)
    }

    override fun setTitle(title: CharSequence?) {
        titleTextView?.text = title
        requestLayout()
    }

    override fun setTitle(titleRes: Int) {
        titleTextView?.setText(titleRes)
        requestLayout()
    }
}

package com.pokemon.iquii.components.textview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import com.pokemon.iquii.R
import com.pokemon.iquii.localizer.PokemonIQUIILocalizer

open class PokemonIquiiTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        setup(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(attrs, defStyleAttr)
    }

    private fun setup(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) {
            return
        }

        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.localizer)
        if (typedArray.hasValue(R.styleable.localizer_text)) {
            @StringRes val textResId = typedArray.getResourceId(R.styleable.localizer_text, 0)
            if (textResId != 0) {
                text = PokemonIQUIILocalizer.get(textResId, context)
            }
        }
        typedArray.recycle()

    }

    private fun getHeight(lineCount: Int): Int {
        var sum: Int = lineCount * getLineCount()
        if (lineCount > 0) {
            sum += (lineCount - 1) * paint.fontSpacing.toInt()
        } else {
            sum += 0
        }
        return (sum / 1.5F).toInt()
    }

}
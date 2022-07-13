package com.pokemon.iquii.components.binding

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import timber.log.Timber
import java.net.URLDecoder

@BindingAdapter(value = ["app:imageUrl"], requireAll = false)
fun loadImage(view: SimpleDraweeView, imageUrl: String?) {
    if (imageUrl != null) {
        try {
            val decodeUrl = URLDecoder.decode(imageUrl, "UTF-8")
            view.setImageURI(decodeUrl)
        } catch (e: Exception) {
            Timber.d("bad url image %s", e.message)
        }
    }
}

@BindingAdapter("app:setColorFilter")
fun setColorFilter(v: ImageView, @ColorRes color : Int) {
    v.setColorFilter(ResourcesCompat.getColor(v.context.resources, color, null))
}

@BindingAdapter("app:onClick")
fun onClick(v : View, onClickListener: View.OnClickListener){
    v.setOnClickListener { onClickListener.onClick(v)
        return@setOnClickListener}
}

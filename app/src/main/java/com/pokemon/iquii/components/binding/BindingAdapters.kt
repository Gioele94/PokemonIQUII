package com.pokemon.iquii.components.binding

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
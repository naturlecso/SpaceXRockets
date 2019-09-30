package hu.naturlecso.spacexrockets.common.binding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter(value = ["url", "placeholder", "size"])
fun ImageView.bindUrl(url: String?, placeholder: Drawable?, size: Float) {
    url ?: return
    placeholder ?: return

    load(url) {
        crossfade(true)
        placeholder(placeholder)
        size(size.toInt())
    }
}

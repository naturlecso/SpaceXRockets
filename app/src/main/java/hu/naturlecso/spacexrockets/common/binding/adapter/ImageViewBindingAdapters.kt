package hu.naturlecso.spacexrockets.common.binding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import hu.naturlecso.spacexrockets.R

@BindingAdapter("url")
fun ImageView.bindImageUrl(url: String? ) {
    if (url.isNullOrEmpty()) {
        return
    }

//    Picasso.get()
//        .load(url)
//        .placeholder(R.drawable.ic_beer) // TODO change to a nicer drawable
//        .into(this)
}

package hu.naturlecso.spacexrockets.common.binding.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun TextView.bindIntegerAsText(int: Int) {
    text = int.toString()
}

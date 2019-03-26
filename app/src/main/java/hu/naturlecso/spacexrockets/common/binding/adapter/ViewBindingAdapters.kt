package hu.naturlecso.spacexrockets.common.binding.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.bindVisibility(visible: Boolean) {
    visibility = decideVisibility(visible)
}

@BindingAdapter("android:visibility")
fun View.bindVisibility(any: Any?) {
    visibility = decideVisibility(any != null)
}

@BindingAdapter("android:visibility")
fun <T> View.bindVisibility(list: List<T>) {
    visibility = decideVisibility(!list.isNullOrEmpty())
}

private fun decideVisibility(visible: Boolean): Int {
    return if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

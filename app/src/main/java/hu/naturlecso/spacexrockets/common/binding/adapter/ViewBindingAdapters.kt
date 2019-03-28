package hu.naturlecso.spacexrockets.common.binding.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["android:visibility", "invisibleType"], requireAll = false)
fun View.bindVisibility(visible: Boolean, invisibleType: Int?) {
    visibility = decideVisibility(visible, invisibleType)
}

@BindingAdapter(value = ["android:visibility", "invisibleType"], requireAll = false)
fun <T> View.bindVisibility(list: List<T>, invisibleType: Int?) {
    visibility = decideVisibility(!list.isNullOrEmpty(), invisibleType)
}

private fun decideVisibility(visible: Boolean, invisibleType: Int?): Int {
    return if (visible) {
        View.VISIBLE
    } else {
        invisibleType ?: View.GONE
    }
}

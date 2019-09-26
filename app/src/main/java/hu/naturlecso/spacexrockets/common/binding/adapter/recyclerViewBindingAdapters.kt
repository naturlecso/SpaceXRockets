package hu.naturlecso.spacexrockets.common.binding.adapter

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.naturlecso.spacexrockets.common.presentation.DividerItemDecoration

@BindingAdapter(value = ["divider", "dividerOrientation", "showLastDivider"], requireAll = false)
fun RecyclerView.bindDivider(divider: Drawable?, orientation: Int?, showLast: Boolean?) {
    divider ?: return

    addItemDecoration(DividerItemDecoration(
        divider = divider,
        orientation = orientation ?: LinearLayout.VERTICAL,
        showLastItem = showLast ?: false)
    )
}

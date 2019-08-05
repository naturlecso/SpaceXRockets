package hu.naturlecso.spacexrockets.common.binding.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hu.naturlecso.spacexrockets.common.binding.Command

@BindingMethods(
    BindingMethod(
            type = SwipeRefreshLayout::class,
            attribute = "refreshing",
            method = "setRefreshing")
)
class SwipeAdapterBindingMethods

@BindingAdapter("refreshCommand")
fun SwipeRefreshLayout.bindRefreshListener(command: Command?) {
    command?.let { setOnRefreshListener { it.execute() } }
}
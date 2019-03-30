package hu.naturlecso.spacexrockets.common.binding.adapter

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.recyclerview.widget.RecyclerView
import hu.naturlecso.spacexrockets.common.binding.Command
import hu.naturlecso.spacexrockets.common.view.ItemActionProvider
import hu.naturlecso.spacexrockets.common.view.BindableRecyclerViewAdapter

@BindingMethods(
        BindingMethod(
                type = RecyclerView::class,
                attribute = "hasFixedSize",
                method = "setHasFixedSize")
)
class RecyclerViewBindingMethods

@BindingAdapter("items")
fun <T> RecyclerView.bindItems(items: List<T>?) {
    if (adapter == null) {
        return
    }

    if (items == null) return

    if (adapter is BindableRecyclerViewAdapter<*>) {
        (adapter as BindableRecyclerViewAdapter<T>).swapItems(items)
    }
}

@BindingAdapter("itemClicked")
fun <T> RecyclerView.bindItemClickedCommand(commandWithParam: ((T) -> Command)?) {
    if (adapter != null && adapter is ItemActionProvider<*>) {
        val actionProvider = adapter as ItemActionProvider<T>

        actionProvider.setOnItemClickedListener(commandWithParam?.run {
            object : ItemActionProvider.OnItemClickedListener<T> {
                override fun onItemClicked(item: T) {
                    invoke(item).execute()
                }
            }
        })
    }
}

package hu.naturlecso.spacexrockets.common.view

interface ItemActionProvider<T> {

    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener<T>?)

    interface OnItemClickedListener<T> {
        fun onItemClicked(item: T)
    }
}

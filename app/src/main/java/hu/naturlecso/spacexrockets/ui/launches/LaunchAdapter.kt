package hu.naturlecso.spacexrockets.ui.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.common.view.BindableRecyclerViewAdapter

class LaunchAdapter : BindableRecyclerViewAdapter<LaunchListItem>() {
    private val VIEW_TYPE_HEADER: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding = if (viewType == VIEW_TYPE_HEADER) {
            DataBindingUtil.inflate(inflater, R.layout.list_item_launch_header, parent, false)
        } else {
            DataBindingUtil.inflate(inflater, R.layout.list_item_launch, parent, false)
        }

        return ViewHolder(binding)
    }

    override fun getItemViewModel(item: LaunchListItem): ViewModel =
        if (item is LaunchListItemHeader) {
            LaunchListItemHeaderViewModel(item.year)
        } else {
            LaunchListItemModelViewModel((item as LaunchListItemModel).launch)
        }

    override val diffCallback: DiffUtil.ItemCallback<LaunchListItem> = object : DiffUtil.ItemCallback<LaunchListItem>() {
        override fun areContentsTheSame(oldItem: LaunchListItem, newItem: LaunchListItem): Boolean =
                oldItem.hashCode() == newItem.hashCode()

        override fun areItemsTheSame(oldItem: LaunchListItem, newItem: LaunchListItem): Boolean =
                oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is LaunchListItemHeader) {
            VIEW_TYPE_HEADER
        } else {
            super.getItemViewType(position)
        }
    }
}

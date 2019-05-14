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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val layout = when(viewType) {
            VIEW_TYPE_LAUNCH -> R.layout.list_item_launch
            VIEW_TYPE_YEAR -> R.layout.list_item_launch_year
            VIEW_TYPE_CHART -> R.layout.list_item_launch_chart
            VIEW_TYPE_ROCKET_DESCRIPTION -> R.layout.list_item_launch_rocket_description
            else -> 0
        }

        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layout, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemViewModel(item: LaunchListItem): ViewModel = when (item) {
        is LaunchListItem.LaunchItem -> LaunchListItemLaunchViewModel(item.launch)
        is LaunchListItem.Year -> LaunchListItemYearViewModel(item.year)
        is LaunchListItem.Chart -> LaunchListItemChartViewModel(item.launches)
        is LaunchListItem.RocketDescription -> LaunchListItemRocketDescriptionViewModel(item.rocket)
    }

    override val diffCallback: DiffUtil.ItemCallback<LaunchListItem> = object : DiffUtil.ItemCallback<LaunchListItem>() {
        override fun areContentsTheSame(oldItem: LaunchListItem, newItem: LaunchListItem): Boolean =
                oldItem.hashCode() == newItem.hashCode()

        override fun areItemsTheSame(oldItem: LaunchListItem, newItem: LaunchListItem): Boolean =
                oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is LaunchListItem.LaunchItem -> VIEW_TYPE_LAUNCH
        is LaunchListItem.Year -> VIEW_TYPE_YEAR
        is LaunchListItem.Chart -> VIEW_TYPE_CHART
        is LaunchListItem.RocketDescription -> VIEW_TYPE_ROCKET_DESCRIPTION
    }

    companion object {
        private const val VIEW_TYPE_LAUNCH = 4
        private const val VIEW_TYPE_YEAR = 1
        private const val VIEW_TYPE_CHART = 2
        private const val VIEW_TYPE_ROCKET_DESCRIPTION = 3
    }
}

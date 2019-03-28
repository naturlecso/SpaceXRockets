package hu.naturlecso.spacexrockets.ui.rockets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import hu.naturlecso.spacexrockets.R
import hu.naturlecso.spacexrockets.common.view.BindableRecyclerViewAdapter
import hu.naturlecso.spacexrockets.databinding.ListItemRocketBinding
import hu.naturlecso.spacexrockets.domain.Rocket

class RocketAdapter : BindableRecyclerViewAdapter<Rocket>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemRocketBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_rocket,
                parent,
                false)

        return ViewHolder(binding)
    }

    override fun getItemViewModel(item: Rocket): ViewModel = RocketListItemViewModel(item)

    override val diffCallback: DiffUtil.ItemCallback<Rocket> = object : DiffUtil.ItemCallback<Rocket>() {
        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean = oldItem.id == newItem.id
    }
}

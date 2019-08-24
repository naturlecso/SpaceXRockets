package hu.naturlecso.spacexrockets.ui.binding

import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import hu.naturlecso.spacexrockets.common.binding.Command
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.rocketItem

// TODO find out if clicklistener is reused
@BindingAdapter("items", "itemClicked")
fun EpoxyRecyclerView.bindItems(items: List<Rocket>?, commandWithParam: ((Rocket) -> Command)?) {
    items ?: return
    commandWithParam ?: return

    withModels {
        items.forEach {
            rocketItem {
                id(it.id)
                name(it.name)
                country(it.country)
                engineCount(it.engineCount)
                clickListener { _, _, _, _ ->
                    commandWithParam.invoke(it).execute()
                }
            }
        }
    }
}

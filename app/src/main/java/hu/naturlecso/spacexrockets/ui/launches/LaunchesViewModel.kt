package hu.naturlecso.spacexrockets.ui.launches

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.domain.LaunchStore
import hu.naturlecso.spacexrockets.domain.RocketStore

class LaunchesViewModel(
        rocketStore: RocketStore,
        launchStore: LaunchStore
) : ViewModel() {

    val selectedRocket = RxObservableField(rocketStore.getSelected())

    val launchListItems = RxObservableField(launchStore.getListBySelectedRocket()
            .map { launches -> launches.groupBy { it.year } }
            .map { launchMap -> launchMap.map { launchEntry ->
                launchEntry.value
                        .map { LaunchListItemModel(it) as LaunchListItem }
                        .let { it.toMutableList()
                                    .apply { add(0, LaunchListItemHeader(launchEntry.key)) }
                                    .toList()
                        }
            } }
            .map { it.flatten() }
    )

    val chartEntries = RxObservableField(launchStore.getListBySelectedRocket()
            .map { launches -> launches.groupBy { it.year } }
            .map { launchMap -> launchMap.mapValues { it.value.count() } }
    )

    val noLaunces = RxObservableField(launchStore.getListBySelectedRocket()
            .map { it.isEmpty() }
    )
}

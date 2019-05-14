package hu.naturlecso.spacexrockets.ui.launches

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.domain.Launch
import hu.naturlecso.spacexrockets.domain.LaunchStore
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.domain.RocketStore
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class LaunchesViewModel(
        rocketStore: RocketStore,
        launchStore: LaunchStore
) : ViewModel() {
    val launchListItems = RxObservableField(Flowable.combineLatest(
        rocketStore.getSelected(),
        launchStore.getListBySelectedRocket(),
        BiFunction<Rocket, List<Launch>, List<LaunchListItem>> { rocket, launches ->
            mutableListOf<LaunchListItem>().apply {
                add(LaunchListItem.RocketDescription(rocket))

                if (launches.isNotEmpty()) {
                    add(LaunchListItem.Chart(launches))

                    addAll(launches.groupBy { it.year }
                        .map { launchEntry ->
                            launchEntry.value
                                .map { LaunchListItem.LaunchItem(it) as LaunchListItem }
                                .let { it.toMutableList()
                                    .apply { this.add(0, LaunchListItem.Year(launchEntry.key)) }
                                    .toList()
                                } }
                        .flatten()
                    )
                }
            }.toList()
        }))
}

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

    val rocketWithLaunches = RxObservableField(Flowable.combineLatest(
        rocketStore.getSelected(),
        launchStore.getListBySelectedRocket(),
        BiFunction<Rocket, List<Launch>, Pair<Rocket, List<Launch>>> {
        rocket, launches -> rocket to launches
    }))
}

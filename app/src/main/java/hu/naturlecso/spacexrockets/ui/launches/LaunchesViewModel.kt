package hu.naturlecso.spacexrockets.ui.launches

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.domain.Launch
import hu.naturlecso.spacexrockets.domain.LaunchStore
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.domain.RocketStore
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.processors.BehaviorProcessor

class LaunchesViewModel(
        rocketStore: RocketStore,
        launchStore: LaunchStore
) : ViewModel() {

    private val rocketIdProcessor = BehaviorProcessor.create<String>()

    fun setRocketId(rocketId: String) {
        rocketIdProcessor.onNext(rocketId)
    }

    val rocketWithLaunches = RxObservableField(
        rocketIdProcessor.switchMap {
            Flowable.combineLatest(
                rocketStore.get(it),
                launchStore.getListBySelectedRocket(it),
                BiFunction<Rocket, List<Launch>, Pair<Rocket, List<Launch>>> {
                        rocket, launches -> rocket to launches
                })
        })
}

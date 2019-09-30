package hu.naturlecso.spacexrockets.features.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import hu.naturlecso.spacexrockets.domain.model.Launch
import hu.naturlecso.spacexrockets.domain.model.Rocket
import hu.naturlecso.spacexrockets.domain.store.LaunchStore
import hu.naturlecso.spacexrockets.domain.store.RocketStore
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

    val rocketWithLaunches = rocketIdProcessor.switchMap {
        Flowable.combineLatest(
            rocketStore.get(it),
            launchStore.getListByRocket(it),
            BiFunction<Rocket, List<Launch>, Pair<Rocket, List<Launch>>> {
                    rocket, launches -> rocket to launches
            })
    }.toLiveData()
}

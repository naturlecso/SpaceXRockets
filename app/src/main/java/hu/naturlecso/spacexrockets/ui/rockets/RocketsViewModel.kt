package hu.naturlecso.spacexrockets.ui.rockets

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.common.binding.command
import hu.naturlecso.spacexrockets.common.navigation.NavigationCommand.To
import hu.naturlecso.spacexrockets.common.navigation.Navigator
import hu.naturlecso.spacexrockets.domain.LaunchAction
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.domain.RocketAction
import hu.naturlecso.spacexrockets.domain.RocketStore
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.processors.BehaviorProcessor

class RocketsViewModel(
        private val navigator: Navigator,
        private val rocketStore: RocketStore,
        private val rocketAction: RocketAction,
        private val launchAction: LaunchAction
) : ViewModel() {

    private val filterInactiveRocketsProcessor = BehaviorProcessor.createDefault<Boolean>(false)

    val rockets = RxObservableField(Flowable.combineLatest(rocketStore.getList(), filterInactiveRocketsProcessor,
            BiFunction<List<Rocket>, Boolean, List<Rocket>> { rockets, filter ->
                rockets.filter { it.active || !filter }
            })
    )

    val refreshing = ObservableField<Boolean>(false)

    val inactiveRocketsFiltered = RxObservableField<Boolean>(filterInactiveRocketsProcessor)

    val refreshCommand = command {
        rocketAction.refresh()
            .andThen(launchAction.refresh())
            .doOnSubscribe { refreshing.set(true) }
            .doOnTerminate { refreshing.set(false) }
            .subscribe()
    }

    val navigateToWelcomeCommand = command {
        rocketStore.getList()
            .firstElement()
            .map { it.isEmpty() }
            .filter { it }
            .subscribe { navigator.navigate(To(RocketsFragmentDirections.actionShowWelcomeDialog())) }
    }

    val navigateToLaunchesCommand = command<Rocket> {
        navigator.navigate(To(RocketsFragmentDirections.navigateToLaunches(it.id)))
    }

    val filterInactiveRocketsCommand = command {
        val previousFilterState = filterInactiveRocketsProcessor.value ?: false
        filterInactiveRocketsProcessor.onNext(!previousFilterState)
    }
}

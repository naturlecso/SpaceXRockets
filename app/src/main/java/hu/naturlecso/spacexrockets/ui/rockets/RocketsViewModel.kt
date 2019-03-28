package hu.naturlecso.spacexrockets.ui.rockets

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.RxObservableField
import hu.naturlecso.spacexrockets.common.binding.command
import hu.naturlecso.spacexrockets.common.navigation.Navigator
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.domain.RocketAction
import hu.naturlecso.spacexrockets.domain.RocketStore

class RocketsViewModel(
        private val navigator: Navigator,
        private val rocketStore: RocketStore,
        private val rocketAction: RocketAction
) : ViewModel() {

    val rockets = RxObservableField(rocketStore.getList())

    val navigateToWelcomeCommand = command {
        rocketStore.getList()
            .firstElement()
            .map { it.isEmpty() }
            .filter { it }
            .subscribe { navigator.navigateTo(RocketsFragmentDirections.actionShowWelcomeDialog()) }
    }

    val navigateToLaunchesCommand = command<Rocket> {
        rocketAction.select(it)
            .subscribe { navigator.navigateTo(RocketsFragmentDirections.navigateToLaunches()) }
    }
}

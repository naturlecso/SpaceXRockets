package hu.naturlecso.spacexrockets.ui.rockets

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.command
import hu.naturlecso.spacexrockets.common.navigation.Navigator
import hu.naturlecso.spacexrockets.domain.RocketStore

class RocketsViewModel(
        private val navigator: Navigator,
        private val rocketStore: RocketStore
) : ViewModel() {

    val navigateToWelcomeCommand = command {
        rocketStore.getList()
                .firstElement()
                .map { it.isEmpty() }
                .filter { it }
                .subscribe { navigator.navigateTo(RocketsFragmentDirections.actionShowWelcomeDialog()) }
    }

    val navigateToLaunchesCommand = command {
        // TODO list element navigation
    }
}

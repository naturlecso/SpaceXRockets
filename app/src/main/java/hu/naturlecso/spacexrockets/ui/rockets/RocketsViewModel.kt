package hu.naturlecso.spacexrockets.ui.rockets

import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.command
import hu.naturlecso.spacexrockets.common.navigation.Navigator

class RocketsViewModel(private val navigator: Navigator) : ViewModel() {

    val navigateToLaunchesCommand = command {
        navigator.navigateTo(RocketsFragmentDirections.navigateToLaunches())
    }
}

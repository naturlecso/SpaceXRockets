package hu.naturlecso.spacexrockets.ui.welcome

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.common.binding.command
import hu.naturlecso.spacexrockets.domain.LaunchAction
import hu.naturlecso.spacexrockets.domain.RocketAction

class WelcomeViewModel(
    private val rocketAction: RocketAction,
    private val launchAction: LaunchAction
): ViewModel() {

    val loading: ObservableField<Boolean> = ObservableField(false)

    val refreshCommand = command {
        rocketAction.refresh()
            .andThen(launchAction.refresh())
            .doOnSubscribe { loading.set(true) }
            .doOnTerminate { loading.set(false) }
            .subscribe()
    }
}

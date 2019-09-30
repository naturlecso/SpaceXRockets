package hu.naturlecso.spacexrockets.features.welcome

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import hu.naturlecso.spacexrockets.domain.action.LaunchAction
import hu.naturlecso.spacexrockets.domain.action.RocketAction

class WelcomeViewModel(
    private val rocketAction: RocketAction,
    private val launchAction: LaunchAction
): ViewModel() {

    val loading: ObservableField<Boolean> = ObservableField(false)

    val refreshCommand = hu.naturlecso.spacexrockets.common.binding.command {
        rocketAction.refresh()
            .andThen(launchAction.refresh())
            .doOnSubscribe { loading.set(true) }
            .doOnTerminate { loading.set(false) }
            .subscribe()
    }
}

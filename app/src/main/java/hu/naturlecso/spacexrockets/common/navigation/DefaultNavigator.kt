package hu.naturlecso.spacexrockets.common.navigation

import androidx.navigation.NavDirections
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

class DefaultNavigator: Navigator {

    private val navigationProcessor = PublishProcessor.create<NavDirections>()

    override fun navigationEvents(): Flowable<NavDirections> = navigationProcessor

    override fun navigateTo(navDirections: NavDirections) {
        navigationProcessor.onNext(navDirections)
    }
}

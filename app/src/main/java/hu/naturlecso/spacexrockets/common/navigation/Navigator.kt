package hu.naturlecso.spacexrockets.common.navigation

import androidx.navigation.NavDirections
import io.reactivex.Flowable

interface Navigator {
    fun navigateTo(navDirections: NavDirections)
    fun navigationEvents(): Flowable<NavDirections>
}

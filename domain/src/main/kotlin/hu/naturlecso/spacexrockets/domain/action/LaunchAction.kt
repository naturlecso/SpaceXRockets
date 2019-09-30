package hu.naturlecso.spacexrockets.domain.action

import io.reactivex.Completable

interface LaunchAction {
    fun refresh(): Completable
}

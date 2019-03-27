package hu.naturlecso.spacexrockets.domain

import io.reactivex.Completable

interface LaunchAction {
    fun refresh(): Completable
}

package hu.naturlecso.spacexrockets.domain.action

import io.reactivex.Completable

interface RocketAction {
    fun refresh(): Completable
}

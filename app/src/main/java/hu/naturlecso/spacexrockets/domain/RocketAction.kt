package hu.naturlecso.spacexrockets.domain

import io.reactivex.Completable

interface RocketAction {
    fun refresh(): Completable
}

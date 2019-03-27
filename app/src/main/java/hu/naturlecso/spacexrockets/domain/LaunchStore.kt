package hu.naturlecso.spacexrockets.domain

import io.reactivex.Flowable

interface LaunchStore {
    fun getListBySelectedRocket(): Flowable<List<Launch>>
}

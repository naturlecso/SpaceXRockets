package hu.naturlecso.spacexrockets.domain

import io.reactivex.Flowable

interface LaunchStore {
    fun getListBySelectedRocket(rocketId: String): Flowable<List<Launch>>
}

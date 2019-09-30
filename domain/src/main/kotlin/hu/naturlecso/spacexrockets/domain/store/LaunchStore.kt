package hu.naturlecso.spacexrockets.domain.store

import hu.naturlecso.spacexrockets.domain.model.Launch
import io.reactivex.Flowable

interface LaunchStore {
    fun getListByRocket(rocketId: String): Flowable<List<Launch>>
}

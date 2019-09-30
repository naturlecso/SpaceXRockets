package hu.naturlecso.spacexrockets.domain.store

import hu.naturlecso.spacexrockets.domain.model.Rocket
import io.reactivex.Flowable

interface RocketStore {
    fun getList(): Flowable<List<Rocket>>
    fun get(id: String): Flowable<Rocket>
}

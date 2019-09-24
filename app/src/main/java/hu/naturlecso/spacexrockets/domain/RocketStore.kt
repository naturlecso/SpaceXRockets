package hu.naturlecso.spacexrockets.domain

import io.reactivex.Flowable

interface RocketStore {
    fun getList(): Flowable<List<Rocket>>
    fun get(id: String): Flowable<Rocket>
}

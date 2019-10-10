package hu.naturlecso.spacexrockets.data.store

import hu.naturlecso.spacexrockets.data.cache.RocketDao
import hu.naturlecso.spacexrockets.data.mapper.RocketDataToDomainMapper
import hu.naturlecso.spacexrockets.domain.model.Rocket
import hu.naturlecso.spacexrockets.domain.store.RocketStore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class DefaultRocketStore(
    private val rocketDao: RocketDao,
    private val rocketDataToDomainMapper: RocketDataToDomainMapper
): RocketStore {

    override fun get(id: String): Flowable<Rocket> = rocketDao.get(id)
        .map { rocketDataToDomainMapper.map(it) }
        .subscribeOn(Schedulers.io())

    override fun getList(): Flowable<List<Rocket>> = rocketDao.getAll()
        .map { rocketDataToDomainMapper.mapList(it) }
        .subscribeOn(Schedulers.io())
}

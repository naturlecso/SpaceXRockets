package hu.naturlecso.spacexrockets.data.store

import hu.naturlecso.spacexrockets.data.cache.LaunchDao
import hu.naturlecso.spacexrockets.data.mapper.LaunchDataToDomainMapper
import hu.naturlecso.spacexrockets.domain.model.Launch
import hu.naturlecso.spacexrockets.domain.store.LaunchStore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class DefaultLaunchStore(
    private val launchDao: LaunchDao,
    private val launchDataToDomainMapper: LaunchDataToDomainMapper
): LaunchStore {

    override fun getListByRocket(rocketId: String): Flowable<List<Launch>> = launchDao.getAllByRocket(rocketId)
        .map { launchDataToDomainMapper.mapList(it) }
        .subscribeOn(Schedulers.io())
}

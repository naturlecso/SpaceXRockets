package hu.naturlecso.spacexrockets.data.action

import hu.naturlecso.spacexrockets.data.mapper.RocketApiToDataMapper
import hu.naturlecso.spacexrockets.data.api.SpacexApiService
import hu.naturlecso.spacexrockets.data.cache.RocketDao
import hu.naturlecso.spacexrockets.domain.action.RocketAction
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DefaultRocketAction(
    private val spacexApiService: SpacexApiService,
    private val rocketDao: RocketDao,
    private val rocketApiToDataMapper: RocketApiToDataMapper
): RocketAction {

    override fun refresh(): Completable = spacexApiService.allRockets()
        .map { rocketApiToDataMapper.mapList(it) }
        .flatMapCompletable { Completable.fromAction { rocketDao.replaceAll(it) } }
        .doOnError { Timber.e(it) }
        .onErrorComplete()
        .subscribeOn(Schedulers.io())
}

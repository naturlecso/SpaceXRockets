package hu.naturlecso.spacexrockets.data.action

import hu.naturlecso.spacexrockets.data.api.SpacexApiService
import hu.naturlecso.spacexrockets.data.cache.LaunchDao
import hu.naturlecso.spacexrockets.data.mapper.LaunchApiToDataMapper
import hu.naturlecso.spacexrockets.domain.action.LaunchAction
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DefaultLaunchAction(
    private val spacexApiService: SpacexApiService,
    private val launchDao: LaunchDao,
    private val launchApiToDataMapper: LaunchApiToDataMapper
): LaunchAction {

    override fun refresh(): Completable = spacexApiService.allLaunches()
        .map { launchApiToDataMapper.mapList(it) }
        .flatMapCompletable { Completable.fromAction { launchDao.replaceAll(it) } }
        .doOnError { Timber.e(it) }
        .onErrorComplete()
        .subscribeOn(Schedulers.io())
}

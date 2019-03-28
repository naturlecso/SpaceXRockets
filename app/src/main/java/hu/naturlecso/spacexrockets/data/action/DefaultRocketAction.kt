package hu.naturlecso.spacexrockets.data.action

import hu.naturlecso.spacexrockets.data.api.RocketApiModel
import hu.naturlecso.spacexrockets.data.api.SpacexApiService
import hu.naturlecso.spacexrockets.data.database.RocketDao
import hu.naturlecso.spacexrockets.data.database.RocketDataModel
import hu.naturlecso.spacexrockets.data.memory.SelectedRocketHolder
import hu.naturlecso.spacexrockets.domain.Rocket
import hu.naturlecso.spacexrockets.domain.RocketAction
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class DefaultRocketAction(
    private val spacexApiService: SpacexApiService,
    private val rocketDao: RocketDao,
    private val rocketHolder: SelectedRocketHolder
): RocketAction {

    override fun refresh(): Completable = spacexApiService.allRockets()
        .map { apiModelList -> apiModelList.map { mapApiModelToDataModel(it) } }
        .flatMapCompletable { Completable.fromAction { rocketDao.replaceAll(it) } }
        .subscribeOn(Schedulers.io())

    override fun select(rocket: Rocket): Completable = rocketHolder.set(rocket)
        .subscribeOn(Schedulers.io())

    private fun mapApiModelToDataModel(apiModel: RocketApiModel): RocketDataModel = apiModel.let {
        RocketDataModel(
            id = it.rocketId,
            name = it.rocketName,
            description = it.description,
            country = it.country,
            active = it.active,
            engineCount = it.engines.number
        )
    }
}

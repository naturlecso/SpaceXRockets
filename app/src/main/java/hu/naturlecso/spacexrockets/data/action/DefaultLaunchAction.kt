package hu.naturlecso.spacexrockets.data.action

import hu.naturlecso.spacexrockets.data.api.LaunchApiModel
import hu.naturlecso.spacexrockets.data.api.SpacexApiService
import hu.naturlecso.spacexrockets.data.database.LaunchDao
import hu.naturlecso.spacexrockets.data.database.LaunchDataModel
import hu.naturlecso.spacexrockets.domain.LaunchAction
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class DefaultLaunchAction(
    private val spacexApiService: SpacexApiService,
    private val launchDao: LaunchDao
): LaunchAction {

    override fun refresh(): Completable = spacexApiService.allLaunches()
        .map { apiModelList -> apiModelList.map { mapApiModelToDataModel(it) } }
        .flatMapCompletable { Completable.fromAction {launchDao.replaceAll(it) } }
        .subscribeOn(Schedulers.io())

    private fun mapApiModelToDataModel(apiModel: LaunchApiModel): LaunchDataModel = apiModel.let {
        LaunchDataModel(
            id = it.flightNumber,
            missionName = it.missionName!!,
            date = it.launchDate,
            year = it.launchYear,
            successful = it.launchSuccess,
            missionPatchUrl = it.links.missionPatch,
            rocketId = it.rocket.rocketId!!
        )
    }
}

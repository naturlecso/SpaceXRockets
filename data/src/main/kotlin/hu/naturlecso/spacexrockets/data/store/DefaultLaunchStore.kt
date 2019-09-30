package hu.naturlecso.spacexrockets.data.store

import hu.naturlecso.spacexrockets.data.cache.LaunchDao
import hu.naturlecso.spacexrockets.data.cache.LaunchDataModel
import hu.naturlecso.spacexrockets.domain.model.Launch
import hu.naturlecso.spacexrockets.domain.store.LaunchStore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

class DefaultLaunchStore(
    private val launchDao: LaunchDao
): LaunchStore {

    override fun getListByRocket(rocketId: String): Flowable<List<Launch>> = launchDao.getAllByRocket(rocketId)
        .map { apiModelList -> apiModelList.map { mapDataModelToDomainModel(it) } }
        .subscribeOn(Schedulers.io())

    private fun mapDataModelToDomainModel(dataModel: LaunchDataModel): Launch = dataModel.let {
        Launch(
            missionName = it.missionName,
            date = Instant.ofEpochSecond(it.date).atZone(ZoneId.systemDefault()).toLocalDate(),
            year = it.year,
            successful = it.successful,
            missionPatchUrl = it.missionPatchUrl
        )
    }
}

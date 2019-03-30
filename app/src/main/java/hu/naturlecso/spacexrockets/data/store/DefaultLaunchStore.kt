package hu.naturlecso.spacexrockets.data.store

import hu.naturlecso.spacexrockets.data.database.LaunchDao
import hu.naturlecso.spacexrockets.data.database.LaunchDataModel
import hu.naturlecso.spacexrockets.data.memory.SelectedRocketHolder
import hu.naturlecso.spacexrockets.domain.Launch
import hu.naturlecso.spacexrockets.domain.LaunchStore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

class DefaultLaunchStore(
    private val launchDao: LaunchDao,
    private val rocketHolder: SelectedRocketHolder
): LaunchStore {
    override fun getListBySelectedRocket(): Flowable<List<Launch>> = rocketHolder.get()
        .map { it.id }
        .switchMap { launchDao.getAllByRocket(it) }
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

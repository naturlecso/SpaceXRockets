package hu.naturlecso.spacexrockets.data.store

import hu.naturlecso.spacexrockets.data.cache.RocketDao
import hu.naturlecso.spacexrockets.data.cache.RocketDataModel
import hu.naturlecso.spacexrockets.domain.model.Rocket
import hu.naturlecso.spacexrockets.domain.store.RocketStore
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class DefaultRocketStore(
    private val rocketDao: RocketDao
): RocketStore {

    override fun get(id: String): Flowable<Rocket> = rocketDao.get(id)
        .map { mapDataModelToDomainModel(it) }
        .subscribeOn(Schedulers.io())

    override fun getList(): Flowable<List<Rocket>> = rocketDao.getAll()
        .map { dataModelList -> dataModelList.map { mapDataModelToDomainModel(it) } }
        .subscribeOn(Schedulers.io())

    private fun mapDataModelToDomainModel(dataModel: RocketDataModel): Rocket = dataModel.let {
        Rocket(
            id = it.id,
            name = it.name,
            description = it.description,
            country = it.country,
            engineCount = it.engineCount,
            active = it.active
        )
    }
}
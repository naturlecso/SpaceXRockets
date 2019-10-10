package hu.naturlecso.spacexrockets.data.mapper

import hu.naturlecso.spacexrockets.common.entity.Mapper
import hu.naturlecso.spacexrockets.data.api.RocketApiModel
import hu.naturlecso.spacexrockets.data.cache.RocketDataModel

class RocketApiToDataMapper : Mapper<RocketApiModel, RocketDataModel> {

    override fun map(model: RocketApiModel): RocketDataModel = model.let {
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
